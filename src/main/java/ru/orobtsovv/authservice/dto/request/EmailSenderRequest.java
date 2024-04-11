package ru.orobtsovv.authservice.dto.request;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@Data
@NoArgsConstructor
@Accessors(chain = true)
public class EmailSenderRequest {
    private String subject;
    private String body;
    private String recipient;
}
