package ru.orobtsovv.authservice.dto.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.time.LocalDateTime;

import static ru.orobtsovv.authservice.utils.Constants.DATE_PATTERN;

@Data
@Accessors(chain = true)
@NoArgsConstructor
public class BannedResponse {
    private String message;
    private String reason;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = DATE_PATTERN)
    private LocalDateTime bannedUntil;
}
