package com.example.Lab2.Service;

import com.example.Lab2.Model.Car;
import com.example.Lab2.Model.DTOs.NrCarsStatisticDTO;
import com.example.Lab2.Model.Dealership;
import com.example.Lab2.Model.Mechanic;
import com.example.Lab2.Model.PerformsMaintenance;
import com.example.Lab2.Repository.CarRepository;
import com.example.Lab2.Repository.MaintenanceRepository;
import com.example.Lab2.Repository.MechanicRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class MaintenanceService implements IMaintenanceService{
    @Autowired private MaintenanceRepository maintenanceRepository;

    @Autowired private CarRepository carRepository;

    @Autowired private MechanicRepository mechanicRepository;

    @Override
    public List<PerformsMaintenance> fetchMaintenanceList() {
        return this.maintenanceRepository.findAll();
    }

    @Override
    public PerformsMaintenance one(Long maintenanceID) {
        return this.maintenanceRepository.findById(maintenanceID).get();
    }

    @Override
    public PerformsMaintenance saveMaintenance(PerformsMaintenance maintenance, Long carID, Long mechanicID) {
        Car car = this.carRepository.findById(carID).get();
        List<PerformsMaintenance> carPerformsMaintenance = car.getPerformsMaintenancesList();
        carPerformsMaintenance.add(maintenance);
        car.setPerformsMaintenancesList(carPerformsMaintenance);
        this.carRepository.save(car);

        Mechanic mechanic = this.mechanicRepository.findById(mechanicID).get();
        List<PerformsMaintenance> mechanicPerformsMaintenance = mechanic.getPerformsMaintenancesList();
        mechanicPerformsMaintenance.add(maintenance);
        mechanic.setPerformsMaintenancesList(mechanicPerformsMaintenance);
        this.mechanicRepository.save(mechanic);

        maintenance.setCar(car);
        maintenance.setMechanic(mechanic);

        return this.maintenanceRepository.save(maintenance);
    }

    @Override
    public PerformsMaintenance updateMaintenance(PerformsMaintenance maintenance, Long maintenanceID) {
        PerformsMaintenance maintenance1 = this.maintenanceRepository.findById(maintenanceID).get();
        maintenance1.setDifficulty(maintenance.getDifficulty());
        maintenance1.setUrgency(maintenance.getUrgency());
        return this.maintenanceRepository.save(maintenance1);
    }

    @Override
    public void deleteMaintenance(Long maintenanceID) {
        this.maintenanceRepository.deleteById(maintenanceID);
    }

    @Override
    public List<NrCarsStatisticDTO> fetchCarsMaintenanceStatistic() {
        List<Car> carList = this.carRepository.findAll();
        List<NrCarsStatisticDTO> statisticDTOList = new ArrayList<>();


        for(Car c : carList){
            NrCarsStatisticDTO nrCarsStatisticDTO = new NrCarsStatisticDTO();
            nrCarsStatisticDTO.setCarID(c.getCarID());
            nrCarsStatisticDTO.setCarManufacturer(c.getCarManufacturer());
            nrCarsStatisticDTO.setCarModel(c.getCarModel());
            nrCarsStatisticDTO.setCarRetailPrice(c.getCarRetailPrice());
            nrCarsStatisticDTO.setCarWeight(c.getCarWeight());
            nrCarsStatisticDTO.setCarTopSpeed(c.getCarTopSpeed());
            nrCarsStatisticDTO.setNrMechanics(c.getPerformsMaintenancesList().size());
            statisticDTOList.add(nrCarsStatisticDTO);
        }

        for(int i = 0; i<statisticDTOList.size()-1; i++){
            for(int j = i+1; j<statisticDTOList.size(); j++){
                if(statisticDTOList.get(i).getNrMechanics() < statisticDTOList.get(j).getNrMechanics()){
                    NrCarsStatisticDTO nrCarsStatisticDTO = statisticDTOList.get(i);
                    statisticDTOList.set(i, statisticDTOList.get(j));
                    statisticDTOList.set(j, nrCarsStatisticDTO);
                }
            }
        }


        return statisticDTOList;
    }
}
