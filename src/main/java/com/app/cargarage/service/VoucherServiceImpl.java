package com.app.cargarage.service;

import com.app.cargarage.dto.ResponseDto;
import com.app.cargarage.model.Car;
import com.app.cargarage.model.ScheduleRepairing;
import com.app.cargarage.model.Voucher;
import com.app.cargarage.repository.CarRepository;
import com.app.cargarage.repository.CustomerRepository;
import com.app.cargarage.repository.VoucherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class VoucherServiceImpl implements VoucherService {
    @Autowired
    VoucherRepository voucherRepository;

    @Autowired
    CarRepository carRepository;

    @Override
    public ResponseDto generateVoucher(String carLicensePlate) {
        try {
            Optional<Car> car = carRepository.findCarByLicensePlate(carLicensePlate);
            if (car.isPresent()) {
                car.get().setRepairStatus("Not Carried out");
                Car updatedCar = carRepository.save(car.get());
                Voucher voucher = Voucher.builder()
                        .id(1)
                        .price(45)
                        .car(updatedCar)
                        .build();
                return ResponseDto.builder()
                        .result(voucherRepository.save(voucher))
                        .statusCode(HttpStatus.OK.value())
                        .message("Voucher has been created !!")
                        .build();
            } else {
                return ResponseDto.builder()
                        .result(null)
                        .statusCode(HttpStatus.INTERNAL_SERVER_ERROR.value())
                        .message("There is no car against this license plate")
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
    public ResponseDto deleteVoucher(long voucherId) {
        try {
            Optional<Voucher> voucher = voucherRepository.findById(voucherId);
            if (voucher.isPresent()) {
                voucher.get().setCar(null);
                voucherRepository.save(voucher.get());
                voucherRepository.delete(voucher.get());
                return ResponseDto.builder()
                        .result(null)
                        .message("Voucher is successfully deleted from the database.")
                        .statusCode(HttpStatus.OK.value())
                        .build();
            } else {
                return ResponseDto.builder()
                        .result(null)
                        .message("There is no voucher against this id")
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
    public ResponseDto updateVoucher(Voucher updatedVoucher) {
        try {
            Optional<Voucher> voucher = voucherRepository.findById(updatedVoucher.getId());
            if (voucher.isPresent()) {
                return ResponseDto.builder()
                        .result(voucherRepository.save(updatedVoucher))
                        .message("Voucher is successfully updated in the database.")
                        .statusCode(HttpStatus.OK.value())
                        .build();
            } else {
                return ResponseDto.builder()
                        .result(null)
                        .message("There is no voucher against this id")
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
    public ResponseDto listOfVouchers() {
        try {
            List<Voucher> voucherList = voucherRepository.findAll();
            if (voucherList.isEmpty()) {
                return ResponseDto.builder()
                        .result(voucherList)
                        .message("There is no Voucher created yet in the database")
                        .statusCode(HttpStatus.NOT_FOUND.value())
                        .build();
            } else {
                return ResponseDto.builder()
                        .result(voucherList)
                        .message("This is the list of Vouchers that are in the database")
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
}
