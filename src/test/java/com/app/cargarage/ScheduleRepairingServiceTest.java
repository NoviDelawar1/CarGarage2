package com.app.cargarage;

import com.app.cargarage.model.Car;
import com.app.cargarage.model.RepairOperations;
import com.app.cargarage.model.ScheduleRepairing;
import com.app.cargarage.repository.CarRepository;
import com.app.cargarage.repository.RepairOperationsRepository;
import com.app.cargarage.repository.ScheduleRepairingRepository;
import com.app.cargarage.service.RepairOperationsServiceImpl;
import com.app.cargarage.service.ScheduleRepairingServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@Transactional
public class ScheduleRepairingServiceTest {

    @MockBean
    ScheduleRepairingRepository scheduleRepairingRepository;
    @MockBean
    CarRepository carRepository;

    @Autowired
    ScheduleRepairingServiceImpl scheduleRepairingService;

    @Test
    void createRepairScheduleTest() {
        Car car = Car.builder()
                .id(1)
                .licensePlate("81-pn-pk")
                .repairStatus("pending")
                .build();

        ScheduleRepairing scheduleRepairing = ScheduleRepairing.builder()
                .id(1)
                .car(car)
                .startDateTime(LocalDateTime.now())
                .endDateTime(LocalDateTime.now())
                .status("Under Repairing")
                .build();

        when(carRepository.findCarByLicensePlate(car.getLicensePlate())).thenReturn(Optional.of(car));
        when(carRepository.save(car)).thenReturn(car);
        when(scheduleRepairingRepository.save(scheduleRepairing)).thenReturn(scheduleRepairing);
        assertEquals(scheduleRepairing, scheduleRepairingService.createRepairSchedule("81-pn-pk", scheduleRepairing).getResult());
    }

    @Test
    void listOfRepairSchedulesTest() {
        Car car = Car.builder()
                .id(1)
                .licensePlate("81-pn-pk")
                .repairStatus("pending")
                .build();

        ScheduleRepairing scheduleRepairing = ScheduleRepairing.builder()
                .id(1)
                .car(car)
                .startDateTime(LocalDateTime.now())
                .endDateTime(LocalDateTime.now())
                .status("Under Repairing")
                .build();

        List<ScheduleRepairing> scheduleRepairingList = new ArrayList<>(Collections.singletonList(scheduleRepairing));
        when(scheduleRepairingRepository.findAll()).thenReturn(scheduleRepairingList);
        assertEquals(scheduleRepairingList, scheduleRepairingService.listOfRepairSchedules().getResult());
    }

    @Test
    void updateRepairScheduleTest() {
        Car car = Car.builder()
                .id(1)
                .licensePlate("81-pn-pk")
                .repairStatus("pending")
                .build();

        ScheduleRepairing scheduleRepairing = ScheduleRepairing.builder()
                .id(1)
                .car(car)
                .startDateTime(LocalDateTime.now())
                .endDateTime(LocalDateTime.now())
                .status("Under Repairing")
                .build();

        ScheduleRepairing updatedScheduleRepairing = ScheduleRepairing.builder()
                .id(1)
                .car(car)
                .startDateTime(LocalDateTime.now())
                .endDateTime(LocalDateTime.now())
                .status("Repaired")
                .build();

        when(scheduleRepairingRepository.findById(scheduleRepairing.getId())).thenReturn(Optional.of(scheduleRepairing));
        when(scheduleRepairingRepository.saveAndFlush(updatedScheduleRepairing)).thenReturn(updatedScheduleRepairing);
        assertEquals(updatedScheduleRepairing, scheduleRepairingService.updateScheduleRepairing(updatedScheduleRepairing).getResult());
    }

    @Test
    void deleteRepairScheduleTest() {
        Car car = Car.builder()
                .id(1)
                .licensePlate("81-pn-pk")
                .repairStatus("pending")
                .build();

        ScheduleRepairing scheduleRepairing = ScheduleRepairing.builder()
                .id(1)
                .car(car)
                .startDateTime(LocalDateTime.now())
                .endDateTime(LocalDateTime.now())
                .status("Under Repairing")
                .build();

        when(scheduleRepairingRepository.findById(scheduleRepairing.getId())).thenReturn(Optional.of(scheduleRepairing));
        assertEquals(200, scheduleRepairingService.deleteScheduleRepairing(1).getStatusCode());
    }
}
