package com.app.cargarage.controller;

import com.app.cargarage.dto.ResponseDto;
import com.app.cargarage.model.Appointment;
import com.app.cargarage.service.AppointmentServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/appointments")
public class AppointmentController {

    private final AppointmentServiceImpl appointmentService;

    @Autowired
    public AppointmentController(AppointmentServiceImpl appointmentService) {
        this.appointmentService = appointmentService;
    }

    @PostMapping("/create")
    public ResponseDto create(@RequestBody Appointment appointment, @RequestParam(name = "customer_id") long customerId) {
        return appointmentService.create(appointment, customerId);
    }

    @GetMapping("/list")
    public ResponseDto listOfAppointments() {
        return appointmentService.listOfAppointments();
    }

    @PutMapping("/update")
    public ResponseDto update(@RequestBody Appointment updatedAppointment) {
        return appointmentService.updateAppointment(updatedAppointment);
    }

    @DeleteMapping("/delete")
    public ResponseDto delete(@RequestParam(name = "appointmentId") long appointmentId) {
        return appointmentService.deleteAppointment(appointmentId);
    }
}
