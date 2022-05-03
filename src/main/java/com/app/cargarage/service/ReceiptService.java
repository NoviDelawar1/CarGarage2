package com.app.cargarage.service;

import com.app.cargarage.dto.ResponseDto;
import com.app.cargarage.model.Receipt;

public interface ReceiptService {
    ResponseDto generateReceipt(String carLicensePlate);

    ResponseDto listReceipts();

    ResponseDto getReceiptsByLicensePlate(String licensePlate);

    ResponseDto deleteReceipt(long receiptId);

    ResponseDto updateReceipt(Receipt receipt);

    ResponseDto changeStatusToPaid(long receiptId);
}
