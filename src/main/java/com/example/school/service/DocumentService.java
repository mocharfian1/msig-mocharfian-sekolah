package com.example.school.service;

import com.example.school.model.entity.Document;
import com.example.school.repository.DocumentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DocumentService {
    @Autowired
    private DocumentRepository documentRepository;

    public List<Document> getAllDocuments() {
        return documentRepository.findAll();
    }

    public Document getDocumentById(Long id) {
        return documentRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Document not found with id: " + id));
    }

    public Document addDocument(Document document) {
        return documentRepository.save(document);
    }

    public Document updateDocument(Long id, Document document) {
        Document existingDocument = documentRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Document not found with id: " + id));
        existingDocument.setDocumentType(document.getDocumentType());
        existingDocument.setFilePath(document.getFilePath());
        return documentRepository.save(existingDocument);
    }

    public void deleteDocument(Long id) {
        documentRepository.deleteById(id);
    }
}
