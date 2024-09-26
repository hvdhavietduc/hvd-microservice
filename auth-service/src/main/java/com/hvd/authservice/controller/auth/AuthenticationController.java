package com.hvd.authservice.controller.auth;

import com.hvd.authservice.request.auth.LoginDto;
import com.hvd.authservice.request.auth.RegisterDto;
import com.hvd.authservice.response.auth.AuthenticationResponse;
import com.hvd.authservice.service.auth.AuthenticationService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
@Transactional(rollbackFor = {Exception.class})
public class AuthenticationController {
    private final AuthenticationService authenticationService;

    @PostMapping("/login")
    public ResponseEntity<AuthenticationResponse> authenticate(
            @Valid @RequestBody LoginDto request,
            BindingResult bindingResult
    ) throws Exception {
        return authenticationService.authenticate(request);
    }

    @PostMapping("/register")
    public ResponseEntity<AuthenticationResponse> register(
            @Valid @RequestBody RegisterDto request,
            BindingResult bindingResult
    ) throws Exception {
        return authenticationService.register(request);
    }

}
