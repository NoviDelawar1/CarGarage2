package com.app.cargarage;

import com.app.cargarage.model.Car;
import com.app.cargarage.model.Customer;
import com.app.cargarage.model.Part;
import com.app.cargarage.model.RepairOperations;
import com.app.cargarage.repository.*;
import com.app.cargarage.service.CarServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@Transactional
class CarServiceTest {

    @Autowired
    CarServiceImpl carService;
    @MockBean
    CarRepository carRepository;
    @MockBean
    CustomerRepository customerRepository;
    @MockBean
    CarDocumentRepository carDocumentRepository;
    @MockBean
    RepairOperationsRepository operationsRepository;
    @MockBean
    PartRepository partRepository;

    @Test
    void addRepairingActionsInCarTest() {
        Customer customer = Customer.builder()
                .id(1L)
                .phoneNumber("0612066676")
                .address("potvisstrat, Amsterdam")
                .surname("Idris Delawar")
                .build();

        Car car = Car.builder()
                .id(1)
                .customer(customer)
                .licensePlate("81-pn-pk")
                .repairStatus("pending")
                .build();

        RepairOperations repairOperation = RepairOperations.builder()
                .id(1)
                .repairAction("To change the brakes")
                .price(5000)
                .build();

        when(carRepository.findCarByLicensePlate(car.getLicensePlate())).thenReturn(Optional.of(car));
        when(operationsRepository.findById(repairOperation.getId())).thenReturn(Optional.of(repairOperation));
        when(carRepository.saveAndFlush(car)).thenReturn(car);

        assertEquals(car.getRepairOperationsList(), carService.addRepairingActionsInCar("81-pn-pk", 1).getResult());
    }

    @Test
    void installPartsInCarTest() {
        Customer customer = Customer.builder()
                .id(1L)
                .phoneNumber("0612066676")
                .address("potvisstrat, Amsterdam")
                .surname("Idris Delawar")
                .build();

        Car car = Car.builder()
                .id(1)
                .customer(customer)
                .licensePlate("81-pn-pk")
                .repairStatus("pending")
                .build();

        Part part = Part.builder()
                .id(1)
                .name("Brake")
                .price(500)
                .build();

        when(carRepository.findCarByLicensePlate(car.getLicensePlate())).thenReturn(Optional.of(car));
        when(partRepository.findById(part.getId())).thenReturn(Optional.of(part));
        when(carRepository.saveAndFlush(car)).thenReturn(car);

        assertEquals(car.getPartsList(), carService.installPartsInCar("81-pn-pk", 1).getResult());
    }

    @Test
    void deleteCarTest() {
        Customer customer = Customer.builder()
                .id(1L)
                .phoneNumber("0612066676")
                .address("potvisstrat, Amsterdam")
                .surname("Idris Delawar")
                .build();

        Car car = Car.builder()
                .id(1)
                .customer(customer)
                .licensePlate("81-pn-pk")
                .repairStatus("pending")
                .build();

        when(carRepository.getCarByLicensePlate(car.getLicensePlate())).thenReturn(car);
        when(carRepository.saveAndFlush(car)).thenReturn(car);
        assertEquals(200, carService.deleteCar("81-pn-pk").getStatusCode());
    }

    @Test
    void updateCarTest() {
        Customer customer = Customer.builder()
                .id(1L)
                .phoneNumber("0612066676")
                .address("potvisstrat, Amsterdam")
                .surname("Idris Delawar")
                .build();

        Car car = Car.builder()
                .id(1)
                .customer(customer)
                .licensePlate("81-pn-pk")
                .repairStatus("pending")
                .build();

        Car updatedCar = Car.builder()
                .id(1)
                .customer(customer)
                .licensePlate("81-pn-pk")
                .repairStatus("Repaired")
                .build();

        when(carRepository.getById(car.getId())).thenReturn(car);
        when(carRepository.saveAndFlush(updatedCar)).thenReturn(updatedCar);
        assertEquals(updatedCar, carService.updateCar(updatedCar).getResult());
    }

    @Test
    void addCarTest() {
        Customer customer = Customer.builder()
                .id(1L)
                .phoneNumber("0612066676")
                .address("potvisstrat, Amsterdam")
                .surname("Idris Delawar")
                .build();

        Car car = Car.builder()
                .id(1)
                .customer(customer)
                .licensePlate("81-pn-pk")
                .repairStatus("pending")
                .build();

        when(customerRepository.save(car.getCustomer())).thenReturn(customer);
        when(carRepository.save(car)).thenReturn(car);
        assertEquals(car, carService.addCar(car).getResult());
    }

    @Test
    void listOfCarsTest() {
        Customer customer = Customer.builder()
                .id(1L)
                .phoneNumber("0612066676")
                .address("potvisstrat, Amsterdam")
                .surname("Idris Delawar")
                .build();

        Car car = Car.builder()
                .id(1)
                .customer(customer)
                .licensePlate("81-pn-pk")
                .repairStatus("pending")
                .build();

        List<Car> carsList = new ArrayList<>();
        carsList.add(car);

        when(carRepository.findAll()).thenReturn(carsList);
        assertEquals(carsList, carService.listOfCars().getResult());
    }

    @Test
    void getCustomerByCarLicensePlateTest() {
        Customer customer = Customer.builder()
                .id(1L)
                .phoneNumber("0612066676")
                .address("potvisstrat, Amsterdam")
                .surname("Idris Delawar")
                .build();

        Car car = Car.builder()
                .id(1)
                .customer(customer)
                .licensePlate("81-pn-pk")
                .repairStatus("pending")
                .build();

        when(carRepository.findCarByLicensePlate(car.getLicensePlate())).thenReturn(Optional.of(car));

        assertEquals(car.getCustomer(), carService.getCustomerByCarLicensePlate(car.getLicensePlate()).getResult());
    }
}
