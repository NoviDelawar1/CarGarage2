package com.app.cargarage.controller;

import com.app.cargarage.dto.ResponseDto;
import com.app.cargarage.model.Customer;
import com.app.cargarage.model.Part;
import com.app.cargarage.service.PartServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/parts")
public class PartController {
    private final PartServiceImpl partService;

    @Autowired
    public PartController(PartServiceImpl partService) {
        this.partService = partService;
    }

    @PostMapping("/add")
    public ResponseDto addPart(@RequestBody Part part) {
        return partService.save(part);
    }

    @GetMapping("/list")
    public ResponseDto listOfParts() {
        return partService.list();
    }

    @GetMapping("/changeStockOfPart")
    public ResponseDto changeStockOfPart(@RequestParam(name = "partId") long partId,
                                         @RequestParam(name = "quantity") int quantity) {
        return partService.changeStockOfPart(partId, quantity);
    }

    @PutMapping("/update")
    public ResponseDto update(@RequestBody Part updatedPart) {
        return partService.updatePart(updatedPart);
    }

    @DeleteMapping("/delete")
    public ResponseDto delete(@RequestParam(name = "partId") long partId) {
        return partService.deletePart(partId);
    }
}
