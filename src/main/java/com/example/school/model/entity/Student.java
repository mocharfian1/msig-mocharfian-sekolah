package com.example.school.model.entity;

import com.example.school.model.request.RegistrationRequestBody;
import jakarta.persistence.*;

import lombok.Data;

import java.util.Date;

@Entity
@Data
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String email;
    private String password;
    private String name;
    private Date birthDate;
    private String gender;
    private String address;
    private String sessionToken;

    public Student() {}

    public Student(RegistrationRequestBody requestBody) {
        this.email = requestBody.getEmail();
        this.password = requestBody.getPassword();
        this.name = requestBody.getName();
        this.birthDate = requestBody.getBirthDate();
        this.gender = requestBody.getGender();
        this.address = requestBody.getAddress();
    }
}
