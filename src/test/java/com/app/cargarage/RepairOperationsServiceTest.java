package com.app.cargarage;

import com.app.cargarage.model.RepairOperations;
import com.app.cargarage.repository.RepairOperationsRepository;
import com.app.cargarage.service.RepairOperationsServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@Transactional
public class RepairOperationsServiceTest {
    @MockBean
    RepairOperationsRepository repairOperationsRepository;

    @Autowired
    RepairOperationsServiceImpl repairOperationsService;

    @Test
    void listOfRepairOperationsTest() {
        RepairOperations repairOperation = RepairOperations.builder()
                .id(1)
                .repairAction("To change the brakes")
                .price(5000)
                .build();

        List<RepairOperations> repairOperationsList = new ArrayList<>(Collections.singletonList(repairOperation));
        when(repairOperationsRepository.findAll()).thenReturn(repairOperationsList);
        assertEquals(repairOperationsList, repairOperationsService.listOfRepairOperations().getResult());
    }

    @Test
    void addRepairOperationsTest() {
        RepairOperations repairOperation = RepairOperations.builder()
                .id(1)
                .repairAction("To change the brakes")
                .price(5000)
                .build();

        when(repairOperationsRepository.saveAndFlush(repairOperation)).thenReturn(repairOperation);
        assertEquals(repairOperation, repairOperationsService.addRepairOperations(repairOperation).getResult());
    }

    @Test
    void updateRepairOperationsTest() {
        RepairOperations repairOperation = RepairOperations.builder()
                .id(1)
                .repairAction("To change the brakes")
                .price(5000)
                .build();

        RepairOperations updatedRepairOperation = RepairOperations.builder()
                .id(1)
                .repairAction("To change the brakes")
                .price(10000)
                .build();

        when(repairOperationsRepository.findById(repairOperation.getId())).thenReturn(Optional.of(repairOperation));
        when(repairOperationsRepository.saveAndFlush(updatedRepairOperation)).thenReturn(updatedRepairOperation);
        assertEquals(updatedRepairOperation, repairOperationsService.updateRepairOperation(updatedRepairOperation).getResult());
    }

    @Test
    void deleteRepairOperationsTest() {
        RepairOperations repairOperation = RepairOperations.builder()
                .id(1)
                .repairAction("To change the brakes")
                .price(5000)
                .build();

        when(repairOperationsRepository.findById(repairOperation.getId())).thenReturn(Optional.of(repairOperation));
        assertEquals(200, repairOperationsService.deleteRepairOperation(1).getStatusCode());
    }
}
