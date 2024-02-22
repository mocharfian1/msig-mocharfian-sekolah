package com.example.school.repository;

import com.example.school.model.StudentWithKelengkapanResponse;
import com.example.school.model.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {
    Student findByEmail(String email);
    Student findByEmailAndPassword(String email, String password);
    Student findBySessionToken(String sessionToken);
}
