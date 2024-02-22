package com.example.school.model;

import com.example.school.model.entity.Kelengkapan;
import jakarta.persistence.Id;
import lombok.Data;

import java.util.Date;

import jakarta.persistence.*;
import java.util.Date;

@Entity
@Table(name = "student_with_kelengkapan_response")
@Data
public class StudentWithKelengkapanResponse {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String email;
    private String name;
    private Date birthDate;
    private String gender;
    private String address;
    private Integer nilai;
}
