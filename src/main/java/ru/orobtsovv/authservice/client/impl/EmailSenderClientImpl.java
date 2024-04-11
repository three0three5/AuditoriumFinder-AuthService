package ru.orobtsovv.authservice.client.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import ru.orobtsovv.authservice.client.EmailSenderClient;
import ru.orobtsovv.authservice.dto.request.EmailSenderRequest;

import static ru.orobtsovv.authservice.utils.Constants.EMAIL_CODE_MESSAGE_TEXT;
import static ru.orobtsovv.authservice.utils.Constants.EMAIL_CODE_SUBJECT;

@Component
@RequiredArgsConstructor
@Slf4j
public class EmailSenderClientImpl implements EmailSenderClient {
    private final WebClient webClient;
    @Value("${services.email-sender.endpoint}")
    private final String sendEndpoint;

    @Override
    public void send(String email, String code) {
        log.info("Sending code");
        EmailSenderRequest request = new EmailSenderRequest()
                .setBody(EMAIL_CODE_MESSAGE_TEXT.formatted(code))
                .setSubject(EMAIL_CODE_SUBJECT)
                .setRecipient(email);
        String result = webClient.post()
                .uri(sendEndpoint)
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(request)
                .retrieve()
                .bodyToMono(String.class)
                .block();
        log.info("Code sent with response: %s".formatted(result));
    }
}
