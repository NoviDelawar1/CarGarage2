package com.app.cargarage.service;

import com.app.cargarage.dto.ResponseDto;
import com.app.cargarage.model.Customer;

public interface CustomerService {
    ResponseDto addCustomer(Customer customer);

    ResponseDto listOfCustomers();

    ResponseDto deleteCustomer(long customerId);

    ResponseDto updateCustomer(Customer customer);
}
