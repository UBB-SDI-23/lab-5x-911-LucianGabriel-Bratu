package com.example.Lab2.Model.DTOs;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CarDealershipDTO {
    private Long carID;

    private String carManufacturer;

    private String carModel;

    private double carRetailPrice;

    private double carTopSpeed;

    private double carWeight;

    private DealershipDTO dealershipDTO;

    @JsonIgnore
    public CarDealershipDTO getCarWithDealershipObject(){
        return this;
    }
}