package com.app.cargarage.service;

import com.app.cargarage.dto.ResponseDto;
import com.app.cargarage.model.Car;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

public interface CarService {
    ResponseDto addCar(Car car);

    ResponseDto listOfCars();

    ResponseDto getCustomerByCarLicensePlate(String licenseNumber);

    ResponseDto uploadDocument(String licensePlate, MultipartFile document);

    ResponseEntity<byte[]> getDocumentsByCarLicensePlate(String licensePlate);

    ResponseDto addRepairingActionsInCar(String carLicensePlate, long repairingActionId);

    ResponseDto installPartsInCar(String carLicensePlate, long partId);

    ResponseDto deleteCar(String carLicensePlate);

    ResponseDto updateCar(Car car);

    ResponseDto getAllRepairedCarsList();

    ResponseDto getAllUnRepairedCarsList();

    ResponseDto changeStatusToRepaired(String licensePlate);
}
