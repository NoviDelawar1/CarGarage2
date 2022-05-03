package com.app.cargarage.service;

import com.app.cargarage.dto.ResponseDto;
import com.app.cargarage.model.Part;
import com.app.cargarage.repository.PartRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PartServiceImpl implements PartService {
    private final PartRepository partRepository;

    public PartServiceImpl(PartRepository partRepository) {
        this.partRepository = partRepository;
    }


    @Override
    public ResponseDto save(Part part) {
        try {
            return ResponseDto.builder()
                    .result(partRepository.save(part))
                    .message("Part is successfully added in the database")
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
    public ResponseDto list() {
        try {
            List<Part> partList = partRepository.findAll();
            if (partList.isEmpty()) {
                return ResponseDto.builder()
                        .result(partList)
                        .message("There is no part added yet in the database")
                        .statusCode(HttpStatus.NOT_FOUND.value())
                        .build();
            } else {
                return ResponseDto.builder()
                        .result(partList)
                        .message("This is the list of parts that are in the database")
                        .statusCode(HttpStatus.OK.value())
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
    public ResponseDto changeStockOfPart(long partId, int quantity) {
        try {
            Optional<Part> part = partRepository.findById(partId);
            if (part.isPresent()) {
                part.get().setStock(part.get().getStock() + quantity);
                return ResponseDto.builder()
                        .result(partRepository.save(part.get()))
                        .message("New stock amount has been added to the database")
                        .statusCode(HttpStatus.OK.value())
                        .build();
            } else {
                return ResponseDto.builder()
                        .result(null)
                        .message("There is no Part against this Id")
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
    public ResponseDto deletePart(long partId) {
        try {
            Optional<Part> part = partRepository.findById(partId);
            if (part.isPresent()) {
                partRepository.delete(part.get());
                return ResponseDto.builder()
                        .result(null)
                        .message("Part is successfully deleted from the database.")
                        .statusCode(HttpStatus.OK.value())
                        .build();
            } else {
                return ResponseDto.builder()
                        .result(null)
                        .message("There is no part against this id")
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
    public ResponseDto updatePart(Part part) {
        try {
            Optional<Part> existingPart = partRepository.findById(part.getId());
            if (existingPart.isPresent()) {
                return ResponseDto.builder()
                        .result(partRepository.saveAndFlush(part))
                        .message("Part is successfully updated in the database.")
                        .statusCode(HttpStatus.OK.value())
                        .build();
            } else {
                return ResponseDto.builder()
                        .result(null)
                        .message("There is no part against this id that you want to update")
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
