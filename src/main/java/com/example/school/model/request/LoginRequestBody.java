package com.example.school.model.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.Date;

@Data
public class LoginRequestBody {
    @Email(message = "Format email salah")
    @NotEmpty(message = "email tidak boleh kosong")
    private String email;

    @NotEmpty(message = "Password tidak boleh kosong")
    private String password;
}