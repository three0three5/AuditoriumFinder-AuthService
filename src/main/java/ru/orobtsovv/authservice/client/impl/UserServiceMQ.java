package ru.orobtsovv.authservice.client.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;
import ru.orobtsovv.authservice.client.AsyncUserServiceClient;
import ru.orobtsovv.authservice.dto.request.ProfileCreateRequest;
import ru.orobtsovv.authservice.dto.message.RemoveTelegramMessage;
import ru.orobtsovv.authservice.dto.message.TelegramHandleMessage;

import static ru.orobtsovv.authservice.utils.MQConstants.PROFILE_CREATE;
import static ru.orobtsovv.authservice.utils.MQConstants.TELEGRAM_REMOVE;
import static ru.orobtsovv.authservice.utils.MQConstants.TELEGRAM_SAVE;

@Service
@Slf4j
@RequiredArgsConstructor
public class UserServiceMQ implements AsyncUserServiceClient {
    private final RabbitTemplate template;

    @Override
    public void propagateHandle(int userid, String telegramHandle) {
        log.info("Propagating handle to user service");
        template.convertAndSend(TELEGRAM_SAVE, new TelegramHandleMessage(userid, telegramHandle));
    }

    @Override
    public void createNewProfile(ProfileCreateRequest profileCreateRequest) {
        log.info("Creating new profile in user service");
        template.convertAndSend(PROFILE_CREATE, profileCreateRequest);
    }

    @Override
    public void removeTgHandle(int userId) {
        log.info("remove tg handle for %d".formatted(userId));
        template.convertAndSend(TELEGRAM_REMOVE, new RemoveTelegramMessage(userId));
    }
}
