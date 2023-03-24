package com.example.Lab2.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "Cars")
public class Car {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long carID;

    @Column(name = "CarManufacturer")
    @NotBlank(message = "Manufacturer is mandatory")
    private String carManufacturer;

    @Column(name = "CarModel")
    @NotBlank(message = "Model is mandatory")
    private String carModel;

    @Column(name = "CarRetailPrice")
    private double carRetailPrice;

    @Column(name = "CarTopSpeed")
    private double carTopSpeed;

    @Column(name = "CarWeight")
    private double carWeight;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "dealershipID", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private Dealership dealership;

    @OneToMany(mappedBy = "car")
    private List<PerformsMaintenance> performsMaintenancesList;
}
