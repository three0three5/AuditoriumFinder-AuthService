package ru.orobtsovv.authservice.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.orobtsovv.authservice.dto.CommonDTO;
import ru.orobtsovv.authservice.dto.request.PasswordChangeRequest;

@RequestMapping("/pwd")
@Tag(name = "Password reset API")
public interface PasswordChangeController {
    @PostMapping("/")
    ResponseEntity<CommonDTO> passwordChange(@Valid @RequestBody PasswordChangeRequest request);
}
