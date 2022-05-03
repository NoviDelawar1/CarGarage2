package com.app.cargarage;

import com.app.cargarage.model.Car;
import com.app.cargarage.model.Customer;
import com.app.cargarage.repository.CarRepository;
import com.app.cargarage.repository.CustomerRepository;
import com.app.cargarage.service.CustomerServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import javax.transaction.Transactional;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@Transactional
public class CustomerServiceTest {

    @Autowired
    CustomerServiceImpl customerService;
    @MockBean
    CustomerRepository customerRepository;
    @MockBean
    CarRepository carRepository;

    @Test
    void addCustomerTest() {
        Customer customer = Customer.builder()
                .id(1L)
                .phoneNumber("0612066676")
                .address("potvisstrat, Amsterdam")
                .surname("Idris Delawar")
                .build();

        Car car = Car.builder()
                .id(1)
                .customer(customer)
                .licensePlate("81-pn-pk")
                .repairStatus("pending")
                .build();
        List<Car> carList = new ArrayList<>();
        carList.add(car);
        customer.setCarList(carList);
        when(carRepository.saveAllAndFlush(customer.getCarList())).thenReturn(customer.getCarList());
        when(customerRepository.saveAndFlush(customer)).thenReturn(customer);
        assertEquals(customer, customerService.addCustomer(customer).getResult());
    }

    @Test
    void listOfCustomersTest() {
        Customer customer = Customer.builder()
                .id(1L)
                .phoneNumber("0612066676")
                .address("potvisstrat, Amsterdam")
                .surname("Idris Delawar")
                .build();

        List<Customer> customersList = new ArrayList<>();
        customersList.add(customer);

        when(customerRepository.findAll()).thenReturn(customersList);
        assertEquals(customersList, customerService.listOfCustomers().getResult());
    }

    @Test
    void deleteCustomerTest() {
        Customer customer = Customer.builder()
                .id(1L)
                .phoneNumber("0612066676")
                .address("potvisstrat, Amsterdam")
                .surname("Idris Delawar")
                .build();

        when(customerRepository.getById(1L)).thenReturn(customer);
        when(customerRepository.saveAndFlush(customer)).thenReturn(customer);
        assertEquals(200, customerService.deleteCustomer(1).getStatusCode());
    }

    @Test
    void updateCustomerTest() {
        Customer customer = Customer.builder()
                .id(1L)
                .phoneNumber("0612066676")
                .address("potvisstrat, Amsterdam")
                .surname("Idris Delawar")
                .build();

        Customer updatedCustomer = Customer.builder()
                .id(1L)
                .phoneNumber("0612066676")
                .address("potvisstrat, Amsterdam")
                .surname("Idris Delawar")
                .build();

        when(customerRepository.getById(customer.getId())).thenReturn(customer);

        when(customerRepository.saveAndFlush(updatedCustomer)).thenReturn(updatedCustomer);
        assertEquals(updatedCustomer, customerService.updateCustomer(updatedCustomer).getResult());
    }
}
