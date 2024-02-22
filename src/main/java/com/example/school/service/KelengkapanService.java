package com.example.school.service;

import com.example.school.model.entity.Kelengkapan;
import com.example.school.model.entity.Student;
import com.example.school.repository.KelengkapanRepository;
import com.example.school.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Service
public class KelengkapanService {

    @Autowired
    private KelengkapanRepository kelengkapanRepository;

    @Autowired
    private StudentRepository studentRepository;

    public Kelengkapan createKelengkapan(String xToken, MultipartFile fotoRapor, MultipartFile fotoKtp, MultipartFile fotoKk,
                                         MultipartFile fotoAkteKelahiran, MultipartFile fotoSuratKelulusan,
                                         Integer nilai, String namaSekolahSebelumnya, String alamatSekolahSebelumnya) throws IOException {

        Student student = studentRepository.findBySessionToken(xToken);

        if(student == null){
            return null;
        }

        List<Kelengkapan> cekKelengkapan = kelengkapanRepository.findKelengkapanByIdStudent(student.getId());

        Kelengkapan kelengkapan = new Kelengkapan();
        if(!cekKelengkapan.isEmpty()){
            kelengkapan.setId(cekKelengkapan.get(0).getId());
        }
        kelengkapan.setIdStudent(student.getId());
        kelengkapan.setFotoRapor(fotoRapor != null? fotoRapor.getBytes() : null);
        kelengkapan.setFotoKtp(fotoKtp != null ? fotoKtp.getBytes(): null);
        kelengkapan.setFotoKk(fotoKk != null ? fotoKk.getBytes(): null);
        kelengkapan.setFotoAkteKelahiran(fotoAkteKelahiran != null ? fotoAkteKelahiran.getBytes(): null);
        kelengkapan.setFotoSuratKelulusan(fotoSuratKelulusan != null ? fotoSuratKelulusan.getBytes(): null);
        kelengkapan.setNilai(nilai != null ? nilai:0);
        kelengkapan.setNamaSekolahSebelumnya(namaSekolahSebelumnya);
        kelengkapan.setAlamatSekolahSebelumnya(alamatSekolahSebelumnya);

        return kelengkapanRepository.save(kelengkapan);
    }

    public Optional<Kelengkapan> getKelengkapanById(String sessionToken) {
        Student student = studentRepository.findBySessionToken(sessionToken);
        if(student == null){
            return Optional.empty();
        }
        return kelengkapanRepository.findById(student.getId());
    }
}
