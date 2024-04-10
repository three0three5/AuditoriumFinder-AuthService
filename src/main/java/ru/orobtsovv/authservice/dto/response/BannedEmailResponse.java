package ru.orobtsovv.authservice.dto.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.experimental.Accessors;

import java.time.LocalDateTime;

import static ru.orobtsovv.authservice.utils.Constants.DATE_PATTERN;

@Data
@Accessors(chain = true)
public class BannedEmailResponse {
    private String email;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = DATE_PATTERN)
    private LocalDateTime bannedUntil;
    private String reason;
    private int moderatorId;
}
