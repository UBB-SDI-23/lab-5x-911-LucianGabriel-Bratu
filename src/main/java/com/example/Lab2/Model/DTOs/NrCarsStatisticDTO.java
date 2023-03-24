package com.example.Lab2.Model.DTOs;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class NrCarsStatisticDTO {
    private Long carID;

    private String carManufacturer;

    private String carModel;

    private double carRetailPrice;

    private double carTopSpeed;

    private double carWeight;

    private int nrMechanics;
}
