package com.example.school.service;

import com.example.school.helper.md5;
import com.example.school.model.StudentWithKelengkapanResponse;
import com.example.school.model.entity.Student;
import com.example.school.model.request.RegistrationRequestBody;
import com.example.school.repository.StudentRepository;
import com.example.school.repository.StudentWithKelengkapanRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class StudentService {
    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private StudentWithKelengkapanRepository studentWithKelengkapanRepository;

    public List<Student> getAllStudents() {
        return studentRepository.findAll();
    }

    public Student getStudentById(Long id) {
        return studentRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Student not found with id: " + id));
    }

    public Student addStudent(Student student) {
        return studentRepository.save(student);
    }

    public Student updateStudent(Long id, Student student) {
        Student existingStudent = studentRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Student not found with id: " + id));
        existingStudent.setName(student.getName());
        existingStudent.setBirthDate(student.getBirthDate());
        return studentRepository.save(existingStudent);
    }

    public void deleteStudent(Long id) {
        studentRepository.deleteById(id);
    }

    public Student registerStudent(RegistrationRequestBody student) {
        if (studentRepository.findByEmail(student.getEmail()) != null) {
            throw new IllegalArgumentException("Email '" + student.getEmail() + "' sudah terdaftar");
        }

        String encodedPassword = md5.generateMD5(student.getPassword());
        student.setPassword(encodedPassword);

        Student studentToSave = new Student(student);

        return studentRepository.save(studentToSave);
    }

    public Student login(String email, String password) {
        String passwordEncode = md5.generateMD5(password);
        Student student = studentRepository.findByEmailAndPassword(email, passwordEncode);
        if (student != null) {
            String sessionToken = generateSessionToken();
            student.setSessionToken(sessionToken);
            studentRepository.save(student);
        }
        return student;
    }

    private String generateSessionToken() {
        return UUID.randomUUID().toString();
    }

    public Student getStudentByToken(String sessionToken) {
        return studentRepository.findBySessionToken(sessionToken);
    }
    public List<StudentWithKelengkapanResponse> getStudentsWithKelengkapan() {
        return studentWithKelengkapanRepository.findStudentsWithKelengkapan();
    }

}
