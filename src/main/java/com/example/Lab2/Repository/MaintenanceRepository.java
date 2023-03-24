package com.example.Lab2.Repository;

import com.example.Lab2.Model.PerformsMaintenance;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MaintenanceRepository extends JpaRepository<PerformsMaintenance, Long> {
}
