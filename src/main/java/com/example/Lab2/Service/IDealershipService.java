package com.example.Lab2.Service;

import com.example.Lab2.Model.Car;
import com.example.Lab2.Model.DTOs.DealershipDTO;
import com.example.Lab2.Model.DTOs.DealershipStatisticDTO;
import com.example.Lab2.Model.Dealership;

import java.util.List;

public interface IDealershipService {
    Dealership saveDealership(Dealership dealership);
    //read
    List<DealershipDTO> fetchDealershipDTOList();

    List<Dealership> fetchDealershipList();
    Dealership one(Long dealershipID);
    //update
    Dealership updateDealership(Dealership dealership, Long dealershipID);

    List<DealershipStatisticDTO> fetchStatisticForDealershipsInventories();
    //delete
    void deleteDealership(Long dealershipID);
}