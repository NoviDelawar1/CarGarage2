package com.app.cargarage.service;

import com.app.cargarage.dto.ResponseDto;
import com.app.cargarage.model.Customer;
import com.app.cargarage.repository.CarRepository;
import com.app.cargarage.repository.CustomerRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerServiceImpl implements CustomerService {
    private final CustomerRepository customerRepository;
    private final CarRepository carRepository;

    public CustomerServiceImpl(CustomerRepository customerRepository, CarRepository carRepository) {
        this.customerRepository = customerRepository;
        this.carRepository = carRepository;
    }

    @Override
    public ResponseDto addCustomer(Customer customer) {
        try {
            carRepository.saveAllAndFlush(customer.getCarList());
            return ResponseDto.builder()
                    .result(customerRepository.saveAndFlush(customer))
                    .message("Customer is successfully added in the database")
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
    public ResponseDto listOfCustomers() {
        try {
            List<Customer> customerList = customerRepository.findAll();
            if (customerList.isEmpty()) {
                return ResponseDto.builder()
                        .result(customerList)
                        .message("There is no customer registered yet in the database")
                        .statusCode(HttpStatus.NOT_FOUND.value())
                        .build();
            } else {
                return ResponseDto.builder()
                        .result(customerList)
                        .message("This is the list of customers that are in the database")
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
    public ResponseDto deleteCustomer(long customerId) {
        try {
            Customer customer = customerRepository.getById(customerId);
            if (customer != null) {
                customer.setCarList(null);
                customerRepository.saveAndFlush(customer);
                customerRepository.delete(customer);
                return ResponseDto.builder()
                        .result(null)
                        .message("Customer is successfully deleted from the database.")
                        .statusCode(HttpStatus.OK.value())
                        .build();
            } else {
                return ResponseDto.builder()
                        .result(null)
                        .message("There is no customer against this id")
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
    public ResponseDto updateCustomer(Customer customer) {
        try {
            Customer existingCustomer = customerRepository.getById(customer.getId());
            if (existingCustomer != null) {
                return ResponseDto.builder()
                        .result(customerRepository.saveAndFlush(customer))
                        .message("Customer is successfully updated in the database.")
                        .statusCode(HttpStatus.OK.value())
                        .build();
            } else {
                return ResponseDto.builder()
                        .result(null)
                        .message("There is no customer against this id that you want to update")
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
