package com.example.school.service;

import com.example.school.model.entity.AcademicRecord;
import com.example.school.repository.AcademicRecordRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AcademicRecordService {
    @Autowired
    private AcademicRecordRepository academicRecordRepository;

    public List<AcademicRecord> getAllAcademicRecords() {
        return academicRecordRepository.findAll();
    }

    public AcademicRecord getAcademicRecordById(Long id) {
        return academicRecordRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Academic record not found with id: " + id));
    }

    public AcademicRecord addAcademicRecord(AcademicRecord academicRecord) {
        return academicRecordRepository.save(academicRecord);
    }

    public AcademicRecord updateAcademicRecord(Long id, AcademicRecord academicRecord) {
        AcademicRecord existingRecord = academicRecordRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Academic record not found with id: " + id));
        existingRecord.setSubject(academicRecord.getSubject());
        existingRecord.setGrade(academicRecord.getGrade());
        return academicRecordRepository.save(existingRecord);
    }

    public void deleteAcademicRecord(Long id) {
        academicRecordRepository.deleteById(id);
    }
}
