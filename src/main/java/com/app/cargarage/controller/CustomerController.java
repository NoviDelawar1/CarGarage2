package com.app.cargarage.controller;

import com.app.cargarage.dto.ResponseDto;
import com.app.cargarage.model.Car;
import com.app.cargarage.model.Customer;
import com.app.cargarage.service.CustomerServiceImpl;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/customer")
public class CustomerController {
    private final CustomerServiceImpl customerService;

    public CustomerController(CustomerServiceImpl customerService) {
        this.customerService = customerService;
    }

    @PostMapping("/add")
    public ResponseDto addCustomer(@RequestBody Customer customer) {
        return customerService.addCustomer(customer);
    }

    @GetMapping("/list")
    public ResponseDto listOfCustomers() {
        return customerService.listOfCustomers();
    }

    @PutMapping("/update")
    public ResponseDto update(@RequestBody Customer customer) {
        return customerService.updateCustomer(customer);
    }

    @DeleteMapping("/delete")
    public ResponseDto delete(@RequestParam(name = "customerId") long customerId) {
        return customerService.deleteCustomer(customerId);
    }
}
