package ru.orobtsovv.authservice.dto.message;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TelegramHandleMessage {
    private int userid;
    private String telegramHandle;
}
