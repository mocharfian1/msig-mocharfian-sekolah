package com.example.school.repository;

import com.example.school.model.entity.Prestasi;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PrestasiRepository extends JpaRepository<Prestasi, Long> {
    List<Prestasi> findByIdStudent(Long idStudent);
}
