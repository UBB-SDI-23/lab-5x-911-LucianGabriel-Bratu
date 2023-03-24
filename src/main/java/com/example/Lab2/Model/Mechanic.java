package com.example.Lab2.Model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "Mechanics")
public class Mechanic {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long mechanicID;

    @Column(name = "Name")
    @NotBlank(message = "Mechanic name is mandatory")
    private String name;

    @Column(name = "CNP")
    @NotBlank(message = "Mechanic CNP is mandatory")
    private String cnp;

    @Column(name = "Address")
    private String address;

    @Column(name = "Experience")
    private int experience;

    @Column(name = "FavouriteCarBrand")
    private String favouriteCarBrand;

    @OneToMany(mappedBy = "mechanic")
    private List<PerformsMaintenance> performsMaintenancesList;
}
