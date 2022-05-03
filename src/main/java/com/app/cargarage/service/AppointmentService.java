package com.app.cargarage.service;

import com.app.cargarage.dto.ResponseDto;
import com.app.cargarage.model.Appointment;

public interface AppointmentService {

    ResponseDto create(Appointment appointment, long customerId);

    ResponseDto listOfAppointments();

    ResponseDto deleteAppointment(long appointmentId);

    ResponseDto updateAppointment(Appointment updatedAppointment);

}
