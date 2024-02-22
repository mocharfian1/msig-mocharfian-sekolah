package com.example.school.controller;

import com.example.school.model.entity.Prestasi;
import com.example.school.model.entity.Response;
import com.example.school.service.PrestasiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/prestasi")
public class PrestasiController {

    @Autowired
    private PrestasiService prestasiService;

    @GetMapping("/")
    public ResponseEntity<Response> getAllPrestasi() {
        List<Prestasi> prestasiList = prestasiService.getAllPrestasi();
        Response response = new Response(HttpStatus.OK.value(), "Success", prestasiList);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Response> getPrestasiById(@PathVariable Long id) {
        Optional<Prestasi> prestasi = prestasiService.getPrestasiById(id);
        if (prestasi.isPresent()) {
            Response response = new Response(HttpStatus.OK.value(), "Success", prestasi.get());
            return new ResponseEntity<>(response, HttpStatus.OK);
        } else {
            Response response = new Response(HttpStatus.NOT_FOUND.value(), "Prestasi not found", null);
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/")
    public ResponseEntity<Response> createPrestasi(@RequestBody Prestasi prestasi) {
        Prestasi createdPrestasi = prestasiService.createPrestasi(prestasi);
        Response response = new Response(HttpStatus.CREATED.value(), "Prestasi created successfully", createdPrestasi);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Response> updatePrestasi(@PathVariable Long id, @RequestBody Prestasi prestasiDetails) {
        Prestasi updatedPrestasi = prestasiService.updatePrestasi(id, prestasiDetails);
        Response response = new Response(HttpStatus.OK.value(), "Prestasi updated successfully", updatedPrestasi);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Response> deletePrestasi(@PathVariable Long id) {
        prestasiService.deletePrestasi(id);
        Response response = new Response(HttpStatus.NO_CONTENT.value(), "Prestasi deleted successfully", null);
        return new ResponseEntity<>(response, HttpStatus.NO_CONTENT);
    }

    @GetMapping("/student/{idStudent}")
    public ResponseEntity<Response> getPrestasiByStudentId(@PathVariable Long idStudent) {
        List<Prestasi> prestasiList = prestasiService.getPrestasiByStudentId(idStudent);
        if (!prestasiList.isEmpty()) {
            Response response = new Response(HttpStatus.OK.value(), "Success", prestasiList);
            return new ResponseEntity<>(response, HttpStatus.OK);
        } else {
            Response response = new Response(HttpStatus.NOT_FOUND.value(), "Prestasi for student not found", null);
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        }
    }
}
