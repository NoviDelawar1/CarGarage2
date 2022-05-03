package com.app.cargarage.service;

import com.app.cargarage.dto.ResponseDto;
import com.app.cargarage.model.RepairOperations;

public interface RepairOperationsService {

    ResponseDto addRepairOperations(RepairOperations repairOperations);
    ResponseDto listOfRepairOperations();

    ResponseDto updateRepairOperation(RepairOperations updatedRepairOperation);

    ResponseDto deleteRepairOperation(long operationId);
}
