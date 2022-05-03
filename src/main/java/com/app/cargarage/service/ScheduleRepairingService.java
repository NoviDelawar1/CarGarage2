package com.app.cargarage.service;

import com.app.cargarage.dto.ResponseDto;
import com.app.cargarage.model.RepairOperations;
import com.app.cargarage.model.ScheduleRepairing;

public interface ScheduleRepairingService {
    ResponseDto createRepairSchedule(String licensePlate, ScheduleRepairing scheduleRepairing);

    ResponseDto listOfRepairSchedules();

    ResponseDto updateScheduleRepairing(ScheduleRepairing updatedScheduleRepairing);

    ResponseDto deleteScheduleRepairing(long scheduleId);
}
