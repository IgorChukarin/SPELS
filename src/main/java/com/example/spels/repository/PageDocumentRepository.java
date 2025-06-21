package com.example.spels.repository;

import com.example.spels.model.PageDocument;
import com.example.spels.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PageDocumentRepository extends JpaRepository<PageDocument, Integer> {
}
