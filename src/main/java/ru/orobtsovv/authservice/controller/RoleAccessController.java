package ru.orobtsovv.authservice.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.orobtsovv.authservice.dto.request.ClientCredentialsRequest;
import ru.orobtsovv.authservice.dto.response.JwtTokenResponse;
import ru.orobtsovv.authservice.dto.response.TokenResponse;

@Tag(name = "Role Access API")
@RequestMapping("/access")
public interface RoleAccessController {
    @PostMapping("/moderator")
    ResponseEntity<TokenResponse> getAccessForModerator(@RequestBody ClientCredentialsRequest request);

    @PostMapping("/tgbot")
    ResponseEntity<JwtTokenResponse> getAccessForTelegramBot(@RequestBody ClientCredentialsRequest request);
}
