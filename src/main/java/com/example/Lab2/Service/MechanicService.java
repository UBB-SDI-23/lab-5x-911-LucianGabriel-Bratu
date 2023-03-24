package com.example.Lab2.Service;

import com.example.Lab2.Model.Mechanic;
import com.example.Lab2.Repository.MechanicRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MechanicService implements IMechanicService{
    @Autowired private MechanicRepository mechanicRepository;

    @Override
    public Mechanic saveMechanic(Mechanic mechanic) {
        return this.mechanicRepository.save(mechanic);
    }

    @Override
    public List<Mechanic> fetchMechanicList() {
        return this.mechanicRepository.findAll();
    }

    @Override
    public Mechanic one(Long mechanicID) {
        return this.mechanicRepository.findById(mechanicID).get();
    }

    @Override
    public Mechanic updateMechanic(Mechanic mechanic, Long mechanicID) {
        Mechanic mechanic1 = this.mechanicRepository.findById(mechanicID).get();
        mechanic1.setName(mechanic.getName());
        mechanic1.setCnp(mechanic.getCnp());
        mechanic1.setAddress(mechanic.getAddress());
        mechanic1.setExperience(mechanic.getExperience());
        mechanic1.setFavouriteCarBrand(mechanic.getFavouriteCarBrand());

        return this.mechanicRepository.save(mechanic1);
    }

    @Override
    public void deleteMechanic(Long mechanicID) {
        this.mechanicRepository.deleteById(mechanicID);
    }
}
