package com.example.Lab2.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PerformsMaintenance {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long maintenanceID;

    private String difficulty;

    private String urgency;

    @ManyToOne
    @JoinColumn(name = "mechanicID", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private Mechanic mechanic;

    @ManyToOne
    @JoinColumn(name = "carID", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private Car car;
}
