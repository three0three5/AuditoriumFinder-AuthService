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
import ru.orobtsovv.authservice.dto.request.SignUpRequest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static ru.orobtsovv.authservice.utils.Constants.EMAIL_CODE_SENT;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Testcontainers(disabledWithoutDocker = true)
public class SignUpControllerIntegrationTest {
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

    @BeforeEach
    public void setUp() {
        baseUrl = "http://localhost:" + port + "/auth";
    }

    @Test
    void givenValidEmailCode_whenSignUp_thenOk() {
        final String email = "smthn@hse.ru";

        EmailRequest request = new EmailRequest();
        request.setEmail(email);
        ResponseEntity<CommonDTO> response = restTemplate.postForEntity(
                baseUrl + "/email/confirm", request, CommonDTO.class);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(EMAIL_CODE_SENT, response.getBody().getMessage());

        Optional<EmailCodeEntity> entity = emailCodeRepository.findById(email);
        assertTrue(entity.isPresent());
        assertEquals(email, entity.get().getEmail());

        SignUpRequest signUp = new SignUpRequest()
                .setEmailCode(entity.get().getCode())
                .setEmail(email)
                .setNickname("Simple nickname")
                .setPassword("Simple password");

        // TODO
    }
}
