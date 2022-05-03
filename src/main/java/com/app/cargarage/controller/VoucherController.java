package com.app.cargarage.controller;

import com.app.cargarage.dto.ResponseDto;
import com.app.cargarage.model.ScheduleRepairing;
import com.app.cargarage.model.Voucher;
import com.app.cargarage.service.VoucherServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/vouchers/")
public class VoucherController {
    @Autowired
    VoucherServiceImpl voucherService;

    @PostMapping("/create")
    public ResponseDto createVoucher(@RequestParam(name = "licensePlate") String licensePlate) {
        return voucherService.generateVoucher(licensePlate);
    }

    @GetMapping("/list")
    public ResponseDto listOfVouchers() {
        return voucherService.listOfVouchers();
    }

    @PutMapping("/update")
    public ResponseDto update(@RequestBody Voucher updatedVoucher) {
        return voucherService.updateVoucher(updatedVoucher);
    }

    @DeleteMapping("/delete")
    public ResponseDto delete(@RequestParam(name = "voucherId") long voucherId) {
        return voucherService.deleteVoucher(voucherId);
    }
}
