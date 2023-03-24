package com.example.Lab2.Service;

import com.example.Lab2.Model.DTOs.NrCarsStatisticDTO;
import com.example.Lab2.Model.PerformsMaintenance;

import java.util.List;

public interface IMaintenanceService {
    List<PerformsMaintenance> fetchMaintenanceList();
    PerformsMaintenance one(Long maintenanceID);
    PerformsMaintenance saveMaintenance(PerformsMaintenance maintenance, Long carID, Long mechanicID);
    PerformsMaintenance updateMaintenance(PerformsMaintenance maintenance, Long maintenanceID);
    void deleteMaintenance(Long maintenanceID);

    List<NrCarsStatisticDTO> fetchCarsMaintenanceStatistic();
}