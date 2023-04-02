package com.example.Lab2.Controller;

import com.example.Lab2.Model.DTOs.NrCarsStatisticDTO;
import com.example.Lab2.Model.Mechanic;
import com.example.Lab2.Model.PerformsMaintenance;
import com.example.Lab2.Service.MaintenanceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
public class MaintenanceController {
    @Autowired private MaintenanceService maintenanceService;

    @GetMapping("/maintenance")
    public List<PerformsMaintenance> fetchMaintenanceList(){
        return this.maintenanceService.fetchMaintenanceList();
    }

    @GetMapping("/maintenance/{maintenanceID}")
    public PerformsMaintenance one(@PathVariable("maintenanceID") Long maintenanceID){
        return this.maintenanceService.one(maintenanceID);
    }

    @GetMapping("/maintenance/nrMechanicsStatistic")
    public List<NrCarsStatisticDTO> fetchCarsMaintenanceStatistic() {
        return this.maintenanceService.fetchCarsMaintenanceStatistic();
    }

    @PostMapping("/maintenance/cars/{carID}/mechanics/{mechanicID}")
    public PerformsMaintenance saveMaintenance(@RequestBody PerformsMaintenance maintenance, @PathVariable("carID") Long carID, @PathVariable("mechanicID") Long mechanicID){
        return this.maintenanceService.saveMaintenance(maintenance, carID, mechanicID);
    }

    @PostMapping("/maintenance/cars/{carID}/mechanics")
    public List<PerformsMaintenance> bulkAddMechanicsForCars(@PathVariable("carID") Long carID, @RequestBody List<Mechanic> mechanicList){
        return this.maintenanceService.bulkAddMechanicsForCars(carID, mechanicList);
    }

    @PutMapping("/maintenance/{maintenanceID}")
    public PerformsMaintenance updateMaintenance(@RequestBody PerformsMaintenance maintenance, @PathVariable("maintenanceID") Long maintenanceID){
        return this.maintenanceService.updateMaintenance(maintenance, maintenanceID);
    }

    @DeleteMapping("/maintenance/{maintenanceID}")
    public String deleteMaintenance(@PathVariable("maintenanceID") Long maintenanceID){
        this.maintenanceService.deleteMaintenance(maintenanceID);
        return "Maintenance successfully deleted";
    }
}
