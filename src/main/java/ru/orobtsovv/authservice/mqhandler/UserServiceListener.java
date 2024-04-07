package ru.orobtsovv.authservice.mqhandler;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import ru.orobtsovv.authservice.dto.message.ProfileDeleteMessage;
import ru.orobtsovv.authservice.service.impl.BanService;

import static ru.orobtsovv.authservice.utils.MQConstants.PROFILE_DELETE_AUTH;

@Component
@Slf4j
@RequiredArgsConstructor
public class UserServiceListener {
    private final BanService banService;

    @Transactional
    @RabbitListener(queues = PROFILE_DELETE_AUTH)
    public void onProfileDelete(ProfileDeleteMessage message) {
        banService.banUser(message);
    }
}
