package com.app.cargarage.controller;

import com.app.cargarage.dto.ResponseDto;
import com.app.cargarage.model.RepairOperations;
import com.app.cargarage.model.ScheduleRepairing;
import com.app.cargarage.service.ScheduleRepairingServiceImpl;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/repairSchedule")
public class RepairScheduleController {

    private final ScheduleRepairingServiceImpl repairingService;

    public RepairScheduleController(ScheduleRepairingServiceImpl repairingService) {
        this.repairingService = repairingService;
    }

    @PostMapping("/create")
    public ResponseDto createSchedule(@RequestBody ScheduleRepairing scheduleRepairing, @RequestParam(name = "licensePlate") String licensePlate) {
        return repairingService.createRepairSchedule(licensePlate, scheduleRepairing);
    }

    @GetMapping("/list")
    public ResponseDto listOfRepairSchedules() {
        return repairingService.listOfRepairSchedules();
    }

    @PutMapping("/update")
    public ResponseDto update(@RequestBody ScheduleRepairing updatedScheduleRepairing) {
        return repairingService.updateScheduleRepairing(updatedScheduleRepairing);
    }

    @DeleteMapping("/delete")
    public ResponseDto delete(@RequestParam(name = "repairingScheduleId") long repairingScheduleId) {
        return repairingService.deleteScheduleRepairing(repairingScheduleId);
    }

}
