package com.app.cargarage.model;

import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "receipts")
public class Receipt {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String status;
    private String carLicensePlate;
    private double repairOperationsAmount;
    private double inspectionAmount;
    private double partsAmount;

    private double totalAmountOfRepairing;

    @ManyToMany(targetEntity = RepairOperations.class, fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
    List<RepairOperations> repairOperationsList = new ArrayList<>();

    @ManyToMany(targetEntity = Part.class, fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
    List<Part> partsList = new ArrayList<>();
}
