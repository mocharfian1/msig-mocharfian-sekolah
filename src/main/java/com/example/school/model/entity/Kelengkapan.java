package com.example.school.model.entity;

import lombok.Data;

import jakarta.persistence.*;

@Data
@Entity
@Table(name = "kelengkapan")
public class Kelengkapan {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "id_student")
    private Long idStudent;

    @Lob
    @Column(name = "foto_rapor")
    private byte[] fotoRapor;

    @Lob
    @Column(name = "foto_ktp")
    private byte[] fotoKtp;

    @Lob
    @Column(name = "foto_kk")
    private byte[] fotoKk;

    @Lob
    @Column(name = "foto_akte_kelahiran")
    private byte[] fotoAkteKelahiran;

    @Lob
    @Column(name = "foto_surat_kelulusan")
    private byte[] fotoSuratKelulusan;

    @Column(name = "nilai")
    private Integer nilai;

    @Column(name = "nama_sekolah_sebelumnya")
    private String namaSekolahSebelumnya;

    @Column(name = "alamat_sekolah_sebelumnya")
    private String alamatSekolahSebelumnya;
}
