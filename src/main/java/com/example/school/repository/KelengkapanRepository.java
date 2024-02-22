package com.example.school.repository;

import com.example.school.model.entity.Kelengkapan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface KelengkapanRepository extends JpaRepository<Kelengkapan, Long> {
    List<Kelengkapan> findKelengkapanByIdStudent(Long id);
}
