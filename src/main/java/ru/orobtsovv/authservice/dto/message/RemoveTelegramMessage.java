package ru.orobtsovv.authservice.dto.message;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class RemoveTelegramMessage {
    private int userid;
}
