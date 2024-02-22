package com.example.school.controller;

import com.example.school.model.StudentWithKelengkapanResponse;
import com.example.school.model.entity.Kelengkapan;
import com.example.school.model.entity.Student;
import com.example.school.model.entity.Response;
import com.example.school.model.request.LoginRequestBody;
import com.example.school.model.request.RegistrationRequestBody;
import com.example.school.service.KelengkapanService;
import com.example.school.service.StudentService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/students")
public class StudentController {
    @Autowired
    private StudentService studentService;

    @Autowired
    private KelengkapanService kelengkapanService;

    @GetMapping
    public ResponseEntity<Response> getAllStudents() {
        List<Student> students = studentService.getAllStudents();
        Response response = new Response(200, "Success", students);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Response> getStudentById(@PathVariable Long id) {
        Student student = studentService.getStudentById(id);
        Response response = new Response(200, "Success", student);
        return ResponseEntity.ok(response);
    }

    @PostMapping
    public ResponseEntity<Response> addStudent(@RequestBody Student student) {
        Student addedStudent = studentService.addStudent(student);
        Response response = new Response(200, "Berhasil menambahkan data", addedStudent);
        return ResponseEntity.ok(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Response> updateStudent(@PathVariable Long id, @RequestBody Student student) {
        Student updatedStudent = studentService.updateStudent(id, student);
        Response response = new Response(200, "Berhasil memperbarui data", updatedStudent);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Response> deleteStudent(@PathVariable Long id) {
        studentService.deleteStudent(id);
        Response response = new Response(200, "Berhasil menghapus data", null);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/register")
    public ResponseEntity<Response> registerStudent(@RequestBody @Valid RegistrationRequestBody student) {
        try{
            Student register = studentService.registerStudent(student);

            if(register != null){
                return ResponseEntity.ok(new Response(HttpStatus.OK.value(), "Pendaftaran Berhasil", register));
            }

            return ResponseEntity.internalServerError().body(new Response(HttpStatus.INTERNAL_SERVER_ERROR.value(), "Pendaftaran Gagal", null));
        } catch (Exception e){
            return ResponseEntity.internalServerError().body(new Response(HttpStatus.INTERNAL_SERVER_ERROR.value(), e.getMessage(), null));
        }
    }

    @PostMapping("/login")
    public ResponseEntity<Response> login(@RequestBody @Valid LoginRequestBody student) {
        Student loggedInStudent = studentService.login(student.getEmail(), student.getPassword());
        if (loggedInStudent != null) {
            HttpHeaders headers = new HttpHeaders();
            headers.add("x-token", loggedInStudent.getSessionToken());

            return new ResponseEntity<>(new Response(HttpStatus.OK.value(), "Login Berhasil", null), headers, HttpStatus.OK);
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(new Response(HttpStatus.UNAUTHORIZED.value(), "Email dan password tidak sesuai", null));
        }
    }

    @PostMapping("kelengkapan/data-profil-kelengkapan")
    public ResponseEntity<Response> createKelengkapan(@RequestHeader("x-token") String xToken,
                                         @RequestParam(name = "fotoRapor", required = false) MultipartFile fotoRapor,
                                         @RequestParam(name = "fotoKTP", required = false) MultipartFile fotoKtp,
                                         @RequestParam(name = "fotoKK", required = false) MultipartFile fotoKk,
                                         @RequestParam(name = "fotoAkteKelahiran", required = false) MultipartFile fotoAkteKelahiran,
                                         @RequestParam(name = "fotoSuratKelulusan", required = false) MultipartFile fotoSuratKelulusan,
                                         @RequestParam(name = "nilai", required = false) Integer nilai,
                                         @RequestParam(name = "namaSekolahSebelumnya", required = false) String namaSekolahSebelumnya,
                                         @RequestParam(name = "alamatSekolahSebelumnya", required = false) String alamatSekolahSebelumnya) {
        try{
            Kelengkapan saveKelengkapan = kelengkapanService.createKelengkapan(xToken, fotoRapor, fotoKtp, fotoKk, fotoAkteKelahiran, fotoSuratKelulusan,
                    nilai, namaSekolahSebelumnya, alamatSekolahSebelumnya);

            if(saveKelengkapan != null){
                return new ResponseEntity<>(new Response(HttpStatus.OK.value(), "Berhasil memperbarui kelengkapan", null),null, HttpStatus.OK.value());
            }
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new Response(HttpStatus.BAD_REQUEST.value(), "Terdapat input yang salah", null));
        } catch (Exception e){
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new Response(HttpStatus.INTERNAL_SERVER_ERROR.value(), "Terjadi kesalahan", null));
        }
    }

    @GetMapping("kelengkapan/{sessionToken}")
    public ResponseEntity<Response> getKelengkapanById(@PathVariable String sessionToken) {
        Optional<Kelengkapan> kelengkapanOptional = kelengkapanService.getKelengkapanById(sessionToken);

        if (kelengkapanOptional.isPresent()) {
            return ResponseEntity.ok(new Response(HttpStatus.OK.value(), "Kelengkapan ditemukan", kelengkapanOptional.get()));
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new Response(HttpStatus.NOT_FOUND.value(), "Kelengkapan tidak ditemukan", null));
        }
    }

    @GetMapping("/byToken")
    public ResponseEntity<Response> getStudentByToken(@RequestParam String sessionToken) {
        Student student = studentService.getStudentByToken(sessionToken);
        if (student != null) {
            Response response = new Response(HttpStatus.OK.value(), "Success", student);
            return new ResponseEntity<>(response, HttpStatus.OK);
        } else {
            Response response = new Response(HttpStatus.NOT_FOUND.value(), "Student not found", null);
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/with-kelengkapan")
    public List<StudentWithKelengkapanResponse> getStudentsWithKelengkapan() {
        return studentService.getStudentsWithKelengkapan();
    }
}
