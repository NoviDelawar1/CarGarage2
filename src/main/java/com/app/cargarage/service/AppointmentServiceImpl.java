package com.app.cargarage.service;

import com.app.cargarage.dto.ResponseDto;
import com.app.cargarage.model.Appointment;
import com.app.cargarage.model.Customer;
import com.app.cargarage.repository.AppointmentRepository;
import com.app.cargarage.repository.CustomerRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AppointmentServiceImpl implements AppointmentService {

    private final AppointmentRepository appointmentRepository;
    private final CustomerRepository customerRepository;

    public AppointmentServiceImpl(AppointmentRepository appointmentRepository, CustomerRepository customerRepository) {
        this.appointmentRepository = appointmentRepository;
        this.customerRepository = customerRepository;
    }

    @Override
    public ResponseDto create(Appointment appointment, long customerId) {
        try {
            Customer customer = customerRepository.getById(customerId);
            if (customer != null) {
                appointment.setCustomer(customer);
            } else {
                return ResponseDto.builder()
                        .result(null)
                        .message("There is no customer against this id")
                        .statusCode(HttpStatus.NOT_FOUND.value())
                        .build();
            }
            return ResponseDto.builder()
                    .result(appointmentRepository.saveAndFlush(appointment))
                    .message("Appointment is successfully created in the database against customer: " + appointment.getCustomer().getSurname())
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
    public ResponseDto listOfAppointments() {
        try {
            List<Appointment> appointments = appointmentRepository.findAll();
            if (appointments.isEmpty()) {
                return ResponseDto.builder()
                        .result(appointments)
                        .message("There is no appointment registered yet in the database")
                        .statusCode(HttpStatus.NOT_FOUND.value())
                        .build();
            } else {
                return ResponseDto.builder()
                        .result(appointments)
                        .message("This is the list of appointments that are in the database")
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
    public ResponseDto deleteAppointment(long appointmentId) {
        try {
            Appointment appointment = appointmentRepository.getById(appointmentId);
            if (appointment != null) {
                appointmentRepository.delete(appointment);
                return ResponseDto.builder()
                        .result(null)
                        .message("Appointment is successfully deleted from the database.")
                        .statusCode(HttpStatus.OK.value())
                        .build();
            } else {
                return ResponseDto.builder()
                        .result(null)
                        .message("There is no appointment against this id")
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
    public ResponseDto updateAppointment(Appointment updatedAppointment) {
        try {
            Appointment appointment = appointmentRepository.getById(updatedAppointment.getId());
            if (appointment != null) {
                return ResponseDto.builder()
                        .result(appointmentRepository.saveAndFlush(updatedAppointment))
                        .message("Appointment is successfully updated in the database.")
                        .statusCode(HttpStatus.OK.value())
                        .build();
            } else {
                return ResponseDto.builder()
                        .result(null)
                        .message("There is no appointment against this id that you want to update")
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
