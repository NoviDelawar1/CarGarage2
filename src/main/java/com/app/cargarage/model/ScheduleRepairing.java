package com.app.cargarage.model;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "schedule_repairing")
public class ScheduleRepairing {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private LocalDateTime startDateTime;
    private LocalDateTime endDateTime;
    private String status;

    @OneToOne(targetEntity = Car.class, fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
    Car car;
}
