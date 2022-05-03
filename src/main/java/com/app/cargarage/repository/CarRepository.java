package com.app.cargarage.repository;

import com.app.cargarage.model.Car;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CarRepository extends JpaRepository<Car, Long> {
    Optional<Car> findCarByLicensePlate(String licensePlate);

    Car getCarByLicensePlate(String licensePlate);

    List<Car> findAllByRepairStatusIgnoreCase(String repairStatus);
}
