package com.app.cargarage.controller;

import com.app.cargarage.dto.ResponseDto;
import com.app.cargarage.model.Receipt;
import com.app.cargarage.model.RepairOperations;
import com.app.cargarage.service.RepairOperationsServiceImpl;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/repairOperations")
public class RepairOperationsController {
    private final RepairOperationsServiceImpl repairOperationsService;

    public RepairOperationsController(RepairOperationsServiceImpl repairOperationsService) {
        this.repairOperationsService = repairOperationsService;
    }

    @PostMapping("/add")
    public ResponseDto add(@RequestBody RepairOperations repairOperations) {
        return repairOperationsService.addRepairOperations(repairOperations);
    }

    @GetMapping("/list")
    public ResponseDto listOfRepairOperations() {
        return repairOperationsService.listOfRepairOperations();
    }

    @PutMapping("/update")
    public ResponseDto update(@RequestBody RepairOperations updatedRepairOperation) {
        return repairOperationsService.updateRepairOperation(updatedRepairOperation);
    }

    @DeleteMapping("/delete")
    public ResponseDto delete(@RequestParam(name = "repairOperationId") long repairOperationId) {
        return repairOperationsService.deleteRepairOperation(repairOperationId);
    }
}
