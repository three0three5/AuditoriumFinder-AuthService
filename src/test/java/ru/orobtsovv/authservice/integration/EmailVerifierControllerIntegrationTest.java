package ru.orobtsovv.authservice.integration;

import com.redis.testcontainers.RedisContainer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.containers.RabbitMQContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import org.testcontainers.utility.DockerImageName;
import ru.orobtsovv.authservice.client.EmailSenderClient;
import ru.orobtsovv.authservice.domain.entity.EmailCodeEntity;
import ru.orobtsovv.authservice.domain.redis.EmailCodeRepository;
import ru.orobtsovv.authservice.dto.CommonDTO;
import ru.orobtsovv.authservice.dto.request.EmailRequest;
import ru.orobtsovv.authservice.exception.email.EmailCodeExpiredException;
import ru.orobtsovv.authservice.exception.email.EmailCodeNotValidException;
import ru.orobtsovv.authservice.service.impl.EmailCodeService;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static ru.orobtsovv.authservice.utils.Constants.EMAIL_CODE_SENT;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Testcontainers(disabledWithoutDocker = true)
public class EmailVerifierControllerIntegrationTest {

    @Container
    private static final RedisContainer REDIS_CONTAINER =
            new RedisContainer(DockerImageName.parse("redis:5.0.3-alpine"))
                    .withExposedPorts(6379);

    @Container
    private static final PostgreSQLContainer<?> POSTGRES_CONTAINER =
            new PostgreSQLContainer<>("postgres:latest")
                    .withDatabaseName("authservice")
                    .withUsername("postgres")
                    .withPassword("postgres");

    @Container
    private static final RabbitMQContainer RABBITMQ_CONTAINER =
            new RabbitMQContainer(DockerImageName.parse("rabbitmq:3.8.17"));

    @DynamicPropertySource
    private static void registerRedisProperties(DynamicPropertyRegistry registry) {
        registry.add("spring.data.redis.host", REDIS_CONTAINER::getHost);
        registry.add("spring.data.redis.port", () -> REDIS_CONTAINER.getMappedPort(6379)
                .toString());
        registry.add("spring.datasource.url", POSTGRES_CONTAINER::getJdbcUrl);
        registry.add("spring.datasource.username", POSTGRES_CONTAINER::getUsername);
        registry.add("spring.datasource.password", POSTGRES_CONTAINER::getPassword);
        registry.add("spring.rabbitmq.host", RABBITMQ_CONTAINER::getHost);
        registry.add("spring.rabbitmq.port", () -> RABBITMQ_CONTAINER.getMappedPort(5672)
                .toString());
        registry.add("spring.rabbitmq.username", RABBITMQ_CONTAINER::getAdminUsername);
        registry.add("spring.rabbitmq.password", RABBITMQ_CONTAINER::getAdminPassword);
    }

    @LocalServerPort
    private int port;
    @Autowired
    private TestRestTemplate restTemplate;
    private String baseUrl;
    @MockBean
    private EmailSenderClient client;
    @Autowired
    private EmailCodeRepository emailCodeRepository;
    @Autowired
    EmailCodeService emailCodeService;

    @BeforeEach
    public void setUp() {
        baseUrl = "http://localhost:" + port + "/auth/email";
    }

    @Test
    void contextLoads() {}

    @Test
    void givenEmail_whenSendVerificationCode_thenOk() {
        EmailRequest request = new EmailRequest();
        request.setEmail("someemail@edu.hse.ru");
        ResponseEntity<CommonDTO> response = restTemplate.postForEntity(
                baseUrl + "/confirm", request, CommonDTO.class);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals("Код отправлен", response.getBody().getMessage());
    }

    @Test
    void givenEmail_whenSendVerificationCode_thenCodeStored() {
        final String email = "otheremail@hse.ru";

        EmailRequest request = new EmailRequest();
        request.setEmail(email);
        ResponseEntity<CommonDTO> response = restTemplate.postForEntity(
                baseUrl + "/confirm", request, CommonDTO.class);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(EMAIL_CODE_SENT, response.getBody().getMessage());

        Optional<EmailCodeEntity> entity = emailCodeRepository.findById(email);
        assertTrue(entity.isPresent());
        assertEquals(email, entity.get().getEmail());
    }

    @Test
    void givenSentCode_whenVerify_thenNoExceptionThrown() {
        final String email = "smthn@hse.ru";

        EmailRequest request = new EmailRequest();
        request.setEmail(email);
        ResponseEntity<CommonDTO> response = restTemplate.postForEntity(
                baseUrl + "/confirm", request, CommonDTO.class);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(EMAIL_CODE_SENT, response.getBody().getMessage());

        Optional<EmailCodeEntity> entity = emailCodeRepository.findById(email);
        assertTrue(entity.isPresent());
        assertEquals(email, entity.get().getEmail());

        emailCodeService.verifyEmail(email, entity.get().getCode());
    }

    @Test
    void givenSentCode_whenVerifyTwice_thenExceptionThrown() {
        final String email = "smthn@hse.ru";

        EmailRequest request = new EmailRequest();
        request.setEmail(email);
        ResponseEntity<CommonDTO> response = restTemplate.postForEntity(
                baseUrl + "/confirm", request, CommonDTO.class);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(EMAIL_CODE_SENT, response.getBody().getMessage());

        Optional<EmailCodeEntity> entity = emailCodeRepository.findById(email);
        assertTrue(entity.isPresent());
        assertEquals(email, entity.get().getEmail());

        emailCodeService.verifyEmail(email, entity.get().getCode());
        assertThrows(EmailCodeExpiredException.class,
                () -> emailCodeService.verifyEmail(email, entity.get().getCode()));
    }

    @Test
    void givenEmail_whenSendTwice_thenExceptionThrown() {
        final String email = "awdadawdawdawd@hse.ru";

        EmailRequest request = new EmailRequest();
        request.setEmail(email);
        ResponseEntity<CommonDTO> response = restTemplate.postForEntity(
                baseUrl + "/confirm", request, CommonDTO.class);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(EMAIL_CODE_SENT, response.getBody().getMessage());

        Optional<EmailCodeEntity> entity = emailCodeRepository.findById(email);
        assertTrue(entity.isPresent());
        assertEquals(email, entity.get().getEmail());

        response = restTemplate.postForEntity(
                baseUrl + "/confirm", request, CommonDTO.class);
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
    }

    @Test
    void givenCodeSent_whenCodeWrongVerify_thenExceptionThrown() {
        final String email = "fsdrvser@hse.ru";

        EmailRequest request = new EmailRequest();
        request.setEmail(email);
        ResponseEntity<CommonDTO> response = restTemplate.postForEntity(
                baseUrl + "/confirm", request, CommonDTO.class);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(EMAIL_CODE_SENT, response.getBody().getMessage());

        Optional<EmailCodeEntity> entity = emailCodeRepository.findById(email);
        assertTrue(entity.isPresent());
        assertEquals(email, entity.get().getEmail());

        assertThrows(EmailCodeNotValidException.class,
                () -> emailCodeService.verifyEmail(email, "; drop table users"));
    }
}
