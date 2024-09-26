package com.hvd.bookservice.response.auth;

import lombok.*;

@Getter
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AuthenticationResponse {
    private String token;
    private String username;
    private String role;
    private String id;
    private String avatar;
}
