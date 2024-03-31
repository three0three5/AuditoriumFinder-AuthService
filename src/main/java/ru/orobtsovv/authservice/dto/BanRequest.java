package ru.orobtsovv.authservice.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.orobtsovv.authservice.dto.validator.ValidDate;

import java.time.LocalDateTime;

import static ru.orobtsovv.authservice.utils.Constants.DATE_PATTERN;

@Data
@NoArgsConstructor
public class BanRequest {
    private int userid;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = DATE_PATTERN)
    @ValidDate
    private LocalDateTime bannedUntil;
    private String reason;
    private int moderatorId;
}
