package com.example.school.repository;

import com.example.school.model.StudentWithKelengkapanResponse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudentWithKelengkapanRepository extends JpaRepository<StudentWithKelengkapanResponse, Long> {

    @Query(value = "select s.id, s.birth_date,s.email, s.name, s.gender, s.address, k.nilai from student as s join kelengkapan as k on k.id_student = s.id", nativeQuery = true)
    List<StudentWithKelengkapanResponse> findStudentsWithKelengkapan();
}