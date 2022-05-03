package com.app.cargarage.repository;

import com.app.cargarage.model.RepairOperations;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RepairOperationsRepository extends JpaRepository<RepairOperations, Long> {
}
