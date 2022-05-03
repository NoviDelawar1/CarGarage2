package com.app.cargarage.repository;

import com.app.cargarage.model.CarDocument;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CarDocumentRepository extends JpaRepository<CarDocument, Long> {
}
