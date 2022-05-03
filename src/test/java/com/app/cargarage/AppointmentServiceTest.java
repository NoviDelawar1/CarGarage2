package com.app.cargarage;

import com.app.cargarage.model.Appointment;
import com.app.cargarage.model.Customer;
import com.app.cargarage.repository.AppointmentRepository;
import com.app.cargarage.repository.CustomerRepository;
import com.app.cargarage.service.AppointmentServiceImpl;
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
class AppointmentServiceTest {
    @Autowired
    AppointmentServiceImpl appointmentService;
    @MockBean
    AppointmentRepository appointmentRepository;
    @MockBean
    CustomerRepository customerRepository;

    @Test
    void createAppointmentTest() {
        Customer customer = Customer.builder()
                .id(1L)
                .phoneNumber("0612066676")
                .address("potvisstrat, Amsterdam")
                .surname("Idris Delawar")
                .build();
        Appointment appointment = Appointment.builder()
                .id(1)
                .description("This is the contract description that will be placed")
                .build();

        when(customerRepository.getById(1L)).thenReturn(customer);

        when(appointmentRepository.saveAndFlush(appointment)).thenReturn(appointment);
        assertEquals(appointment, appointmentService.create(appointment, 1L).getResult());
    }

    @Test
    void listOfAppointmentsTest() {
        Customer customer = Customer.builder()
                .id(1L)
                .phoneNumber("0612066676")
                .address("potvisstrat, Amsterdam")
                .surname("Idris Delawar")
                .build();
        Appointment appointment = Appointment.builder()
                .id(1)
                .description("This is the contract description that will be placed")
                .build();

        appointment.setCustomer(customer);
        List<Appointment> appointmentsList = new ArrayList<>();
        appointmentsList.add(appointment);
        when(appointmentRepository.findAll()).thenReturn(appointmentsList);
        assertEquals(appointmentsList, appointmentService.listOfAppointments().getResult());
    }

    @Test
    void updateAppointmentTest() {
        Appointment appointment = Appointment.builder()
                .id(1)
                .description("This is the contract description that will be placed")
                .build();

        Appointment updatedAppointment = Appointment.builder()
                .id(1)
                .description("Contract Description has been updated")
                .build();

        when(appointmentRepository.getById(appointment.getId())).thenReturn(appointment);
        when(appointmentRepository.saveAndFlush(updatedAppointment)).thenReturn(updatedAppointment);

        assertEquals(updatedAppointment, appointmentService.updateAppointment(updatedAppointment).getResult());
    }

    @Test
    void deleteAppointmentTest() {
        Appointment appointment = Appointment.builder()
                .id(1)
                .description("This is the contract description that will be placed")
                .build();


        when(appointmentRepository.getById(appointment.getId())).thenReturn(appointment);

        assertEquals(200, appointmentService.deleteAppointment(1).getStatusCode());
    }
}
