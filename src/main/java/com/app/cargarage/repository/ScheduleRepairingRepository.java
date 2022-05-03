package com.app.cargarage.repository;

import com.app.cargarage.model.ScheduleRepairing;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ScheduleRepairingRepository extends JpaRepository<ScheduleRepairing, Long> {
}
