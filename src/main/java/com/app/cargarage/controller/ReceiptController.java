package com.app.cargarage.controller;

import com.app.cargarage.dto.ResponseDto;
import com.app.cargarage.model.Part;
import com.app.cargarage.model.Receipt;
import com.app.cargarage.service.ReceiptServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/receipts")
public class ReceiptController {

    ReceiptServiceImpl receiptService;

    @Autowired
    public ReceiptController(ReceiptServiceImpl receiptService) {
        this.receiptService = receiptService;
    }

    @GetMapping("/generate")
    public ResponseDto generateReceipt(@RequestParam(name = "carLicensePlate") String licensePlate) {
        return receiptService.generateReceipt(licensePlate);
    }

    @GetMapping("/list")
    public ResponseDto listOfReceipts() {
        return receiptService.listReceipts();
    }

    @GetMapping("/getReceiptsByLicensePlate")
    public ResponseDto getReceiptsByLicensePlate(@RequestParam(name = "licensePlate") String licensePlate) {
        return receiptService.getReceiptsByLicensePlate(licensePlate);
    }

    @PutMapping("/update")
    public ResponseDto update(@RequestBody Receipt updatedReceipt) {
        return receiptService.updateReceipt(updatedReceipt);
    }

    @DeleteMapping("/delete")
    public ResponseDto delete(@RequestParam(name = "receiptId") long receiptId) {
        return receiptService.deleteReceipt(receiptId);
    }
}
