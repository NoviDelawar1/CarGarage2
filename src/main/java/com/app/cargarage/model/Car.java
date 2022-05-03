package com.app.cargarage.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "cars")
public class Car implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(unique = true)
    private String licensePlate;
    private String repairStatus;

    @OneToOne
    CarDocument carDocument;

    @ManyToOne(targetEntity = Customer.class, cascade = CascadeType.MERGE, fetch = FetchType.EAGER)
    @JsonBackReference
    Customer customer;

    @ManyToMany(targetEntity = RepairOperations.class, cascade = CascadeType.MERGE, fetch = FetchType.LAZY)
    List<RepairOperations> repairOperationsList = new ArrayList<>();

    @ManyToMany(targetEntity = Part.class, fetch = FetchType.LAZY)
    List<Part> partsList = new ArrayList<>();
}
