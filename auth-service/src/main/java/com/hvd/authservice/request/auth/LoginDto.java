package com.hvd.authservice.request.auth;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class LoginDto {
    @Valid
    @NotEmpty(message = "LOGIN_VALIDATION_ERROR_00001-Tên tài khoản không được để trống")
    @NotNull(message = "LOGIN_VALIDATION_ERROR_00002-Tên tài khoản không được để trống")
    @Size(min = 6, max = 20, message = "LOGIN_VALIDATION_ERROR_00003-Tên tài khoản phải từ 6 đến 20 ký tự")
    private String username;

    @Valid
    @NotEmpty(message = "LOGIN_VALIDATION_ERROR_00004-Mật khẩu không được để trống")
    @NotNull(message = "LOGIN_VALIDATION_ERROR_00005-Mật khẩu không được để trống")
    @Size(min = 6, max = 20, message = "LOGIN_VALIDATION_ERROR_00006-Mật khẩu phải từ 6 đến 20 ký tự")
    private String password;

    public void setUsername(String username){
        if(username != null){
            username = username.trim();
        }
        this.username = username;
    }
    public void setPassword(String password){
        if(password != null){
            password = password.trim();
        }
        this.password = password;
    }
}
