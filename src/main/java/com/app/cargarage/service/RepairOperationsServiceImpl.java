package com.app.cargarage.service;

import com.app.cargarage.dto.ResponseDto;
import com.app.cargarage.model.RepairOperations;
import com.app.cargarage.repository.RepairOperationsRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RepairOperationsServiceImpl implements RepairOperationsService {

    private final RepairOperationsRepository repairOperationsRepository;

    public RepairOperationsServiceImpl(RepairOperationsRepository repairOperationsRepository) {
        this.repairOperationsRepository = repairOperationsRepository;
    }

    @Override
    public ResponseDto addRepairOperations(RepairOperations repairOperations) {
        try {
            return ResponseDto.builder()
                    .result(repairOperationsRepository.saveAndFlush(repairOperations))
                    .message("Repair operations is successfully added in the database")
                    .statusCode(HttpStatus.OK.value())
                    .build();
        } catch (Exception e) {
            return ResponseDto.builder()
                    .result(null)
                    .statusCode(HttpStatus.INTERNAL_SERVER_ERROR.value())
                    .message(e.getMessage())
                    .build();
        }
    }

    @Override
    public ResponseDto listOfRepairOperations() {
        try {
            List<RepairOperations> repairOperationsList = repairOperationsRepository.findAll();
            if (repairOperationsList.isEmpty()) {
                return ResponseDto.builder()
                        .result(repairOperationsList)
                        .statusCode(HttpStatus.OK.value())
                        .message("There is no repair operation generated yet in the database")
                        .build();
            } else {
                return ResponseDto.builder()
                        .result(repairOperationsList)
                        .statusCode(HttpStatus.OK.value())
                        .message("This is the list of generated repair operation in the database")
                        .build();
            }
        } catch (Exception e) {
            return ResponseDto.builder()
                    .result(null)
                    .statusCode(HttpStatus.INTERNAL_SERVER_ERROR.value())
                    .message(e.getMessage())
                    .build();
        }
    }

    @Override
    public ResponseDto updateRepairOperation(RepairOperations updatedRepairOperation) {
        try {
            Optional<RepairOperations> repairOperation = repairOperationsRepository.findById(updatedRepairOperation.getId());
            if (repairOperation.isPresent()) {
                return ResponseDto.builder()
                        .result(repairOperationsRepository.saveAndFlush(updatedRepairOperation))
                        .message("Repair Operation is successfully updated in the database.")
                        .statusCode(HttpStatus.OK.value())
                        .build();
            } else {
                return ResponseDto.builder()
                        .result(null)
                        .message("There is no repair operation against this id")
                        .statusCode(HttpStatus.NOT_FOUND.value())
                        .build();
            }
        } catch (Exception e) {
            return ResponseDto.builder()
                    .result(null)
                    .statusCode(HttpStatus.INTERNAL_SERVER_ERROR.value())
                    .message(e.getMessage())
                    .build();
        }
    }

    @Override
    public ResponseDto deleteRepairOperation(long operationId) {
        try {
            Optional<RepairOperations> repairOperation = repairOperationsRepository.findById(operationId);
            if (repairOperation.isPresent()) {
                repairOperationsRepository.delete(repairOperation.get());
                return ResponseDto.builder()
                        .result(null)
                        .message("Repair Operation is successfully deleted from the database.")
                        .statusCode(HttpStatus.OK.value())
                        .build();
            } else {
                return ResponseDto.builder()
                        .result(null)
                        .message("There is no repairOperation against this id")
                        .statusCode(HttpStatus.NOT_FOUND.value())
                        .build();
            }
        } catch (Exception e) {
            return ResponseDto.builder()
                    .result(null)
                    .statusCode(HttpStatus.INTERNAL_SERVER_ERROR.value())
                    .message(e.getMessage())
                    .build();
        }
    }


}
