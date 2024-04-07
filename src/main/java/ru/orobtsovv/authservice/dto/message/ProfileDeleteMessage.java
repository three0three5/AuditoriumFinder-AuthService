package ru.orobtsovv.authservice.dto.message;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ProfileDeleteMessage {
    private int userid;
    private int moderatorId;
    private LocalDateTime bannedUntil;
    private String reason;
}
