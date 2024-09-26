package com.hvd.bookservice.service.auth;

import com.hvd.bookservice.config.jwt_config.JwtService;
import com.hvd.bookservice.logic.userLogic.UserLogic;
import com.hvd.bookservice.model.user.User;
import com.hvd.bookservice.request.auth.LoginDto;
import com.hvd.bookservice.request.auth.RegisterDto;
import com.hvd.bookservice.response.auth.AuthenticationResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ControllerAdvice;

@Service
@RequiredArgsConstructor
@ControllerAdvice
public class AuthenticationService {
    private final UserLogic userLogic;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    public ResponseEntity<AuthenticationResponse> authenticate(LoginDto request) throws Exception {
        User user = userLogic.getUserByUsername(request.getUsername());
        if (user == null) {
            throw new Exception("User not found");
        }

        String password = user.getPassword();
        if (!passwordEncoder.matches(request.getPassword(), password)) {
            throw new Exception("Invalid password");
        }

        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getUsername(),
                        request.getPassword()
                )
        );

        AuthenticationResponse response = AuthenticationResponse.builder()
                .token(jwtService.generateToken(user))
                .username(user.getUsername())
                .id(user.getId())
                .build();
        return new ResponseEntity<>(response, HttpStatus.OK);

    }

    public ResponseEntity<AuthenticationResponse> logout(String userId) throws Exception {
        User user = userLogic.getUserById(userId);
        if (user == null) {
            throw new Exception("User not found");
        }

        AuthenticationResponse response = AuthenticationResponse.builder()
                .token("")
                .username("")
                .role("")
                .id("")
                .avatar("")
                .build();
        return new ResponseEntity<>(response, HttpStatus.OK);

    }

    public ResponseEntity<AuthenticationResponse> register(RegisterDto request) throws Exception {
        User user = userLogic.getUserByUsername(request.getUsername());
        if (user != null) {
            throw new Exception("User already exists");
        }

        user = User.builder()
                .username(request.getUsername())
                .password(passwordEncoder.encode(request.getPassword()))
                .build();

        userLogic.saveUser(user);

        AuthenticationResponse response = AuthenticationResponse.builder()
                .token(jwtService.generateToken(user))
                .username(user.getUsername())
                .id(user.getId())
                .build();
        return new ResponseEntity<>(response, HttpStatus.OK);

    }
}
