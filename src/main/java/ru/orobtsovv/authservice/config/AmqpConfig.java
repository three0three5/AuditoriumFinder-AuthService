package ru.orobtsovv.authservice.config;

import org.springframework.amqp.core.Queue;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import static ru.orobtsovv.authservice.utils.MQConstants.PROFILE_DELETE_AUTH;

@Configuration
public class AmqpConfig {
    @Bean
    public Jackson2JsonMessageConverter jsonMessageConverter() {
        return new Jackson2JsonMessageConverter();
    }

    @Bean
    public Queue profileDeleteQueue() {
        return new Queue(PROFILE_DELETE_AUTH, true);
    }
}
