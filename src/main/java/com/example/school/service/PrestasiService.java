package com.example.school.service;

import com.example.school.exceptions.ResourceNotFoundException;
import com.example.school.model.entity.Prestasi;
import com.example.school.repository.PrestasiRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class PrestasiService {

    @Autowired
    private PrestasiRepository prestasiRepository;

    public List<Prestasi> getAllPrestasi() {
        return prestasiRepository.findAll();
    }

    public Optional<Prestasi> getPrestasiById(Long id) {
        return prestasiRepository.findById(id);
    }

    public Prestasi createPrestasi(Prestasi prestasi) {
        return prestasiRepository.save(prestasi);
    }

    public Prestasi updatePrestasi(Long id, Prestasi prestasiDetails) {
        Prestasi prestasi = prestasiRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Prestasi not found with id " + id));

        prestasi.setDeskripsi(prestasiDetails.getDeskripsi());
        prestasi.setDeskripsiPenghargaan(prestasiDetails.getDeskripsiPenghargaan());
        prestasi.setNilai(prestasiDetails.getNilai());
        prestasi.setIdStudent(prestasiDetails.getIdStudent());

        return prestasiRepository.save(prestasi);
    }

    public void deletePrestasi(Long id) {
        Prestasi prestasi = prestasiRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Prestasi not found with id " + id));

        prestasiRepository.delete(prestasi);
    }

    public List<Prestasi> getPrestasiByStudentId(Long idStudent) {
        return prestasiRepository.findByIdStudent(idStudent);
    }
}
