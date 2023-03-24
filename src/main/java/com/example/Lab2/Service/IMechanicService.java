package com.example.Lab2.Service;

import com.example.Lab2.Model.Car;
import com.example.Lab2.Model.Dealership;
import com.example.Lab2.Model.Mechanic;

import java.util.List;

public interface IMechanicService {
    Mechanic saveMechanic(Mechanic mechanic);
    //read
    List<Mechanic> fetchMechanicList();
    Mechanic one(Long mechanicID);
    //update
    Mechanic updateMechanic(Mechanic mechanic, Long mechanicID);
    //delete
    void deleteMechanic(Long mechanicID);
}
