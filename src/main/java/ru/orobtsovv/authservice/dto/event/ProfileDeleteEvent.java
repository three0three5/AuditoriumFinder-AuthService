package ru.orobtsovv.authservice.dto.event;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ProfileDeleteEvent {
    private int userid;
    private int moderatorId;
    private LocalDateTime bannedUntil;
    private String reason;
}
