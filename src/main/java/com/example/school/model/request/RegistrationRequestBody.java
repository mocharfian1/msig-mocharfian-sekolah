package com.example.school.model.request;

import jakarta.validation.constraints.*;
import lombok.Data;

import java.util.Date;

@Data
public class RegistrationRequestBody {
    @Email(message = "Format email salah")
    @NotEmpty(message = "email tidak boleh kosong")
    private String email;

    @NotEmpty(message = "Password tidak boleh kosong")
    private String password;

    @NotEmpty(message = "Nama tidak boleh kosong")
    private String name;

    @NotNull(message = "Tanggal Lahir tidak boleh kosong")
    private Date birthDate;

    @NotEmpty(message = "Jenis Kelamin tidak boleh kosong")
    private String gender;

    @NotEmpty(message = "Alamat tidak boleh kosong")
    private String address;
}