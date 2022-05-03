package com.app.cargarage.service;

import com.app.cargarage.dto.ResponseDto;
import com.app.cargarage.model.Voucher;

public interface VoucherService {
    ResponseDto generateVoucher(String carLicensePlate);
    ResponseDto deleteVoucher(long voucherId);
    ResponseDto updateVoucher(Voucher updatedVoucher);
    ResponseDto listOfVouchers();
}
