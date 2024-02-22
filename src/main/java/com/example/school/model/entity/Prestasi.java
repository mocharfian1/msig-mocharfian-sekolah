package com.example.school.model.entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "prestasi")
public class Prestasi {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "deskripsi")
    private String deskripsi;

    @Column(name = "deskripsi_penghargaan")
    private String deskripsiPenghargaan;

    @Column(name = "nilai")
    private int nilai;

    @Column(name = "id_student")
    private Long idStudent;
}
