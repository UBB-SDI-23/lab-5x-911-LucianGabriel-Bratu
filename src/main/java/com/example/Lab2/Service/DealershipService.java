package com.example.Lab2.Service;

import com.example.Lab2.Model.Car;
import com.example.Lab2.Model.DTOs.DealershipDTO;
import com.example.Lab2.Model.DTOs.DealershipStatisticDTO;
import com.example.Lab2.Model.Dealership;
import com.example.Lab2.Repository.DealershipRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class DealershipService implements IDealershipService{
    @Autowired
    private DealershipRepository dealershipRepository;

    @Override
    public Dealership saveDealership(Dealership dealership) {
        return this.dealershipRepository.save(dealership);
    }

    public List<Dealership> fetchDealershipList(){
        return  this.dealershipRepository.findAll();
    }

    @Override
    public List<DealershipDTO> fetchDealershipDTOList() {
        List<DealershipDTO> dealershipDTOS = new ArrayList<>();
        List<Dealership> dealershipList =  this.dealershipRepository.findAll();
        for(Dealership dealership : dealershipList){
            DealershipDTO dealershipDTO = new DealershipDTO();
            dealershipDTO.setId(dealership.getDealershipID());
            dealershipDTO.setName(dealership.getName());
            dealershipDTO.setReputation(dealership.getReputation());
            dealershipDTO.setCapacity(dealership.getCapacity());
            dealershipDTO.setHasService(dealership.isHasService());
            dealershipDTO.setOffersTradeIn(dealership.isOffersTradeIn());
            dealershipDTO.setAddress(dealership.getAddress());
            dealershipDTOS.add(dealershipDTO);
        }
        return dealershipDTOS;
    }



    @Override
    public Dealership one(Long dealershipID) {
        return this.dealershipRepository.findById(dealershipID).get();
    }

    @Override
    public Dealership updateDealership(Dealership dealership, Long dealershipID) {
        Dealership foundDealership = this.dealershipRepository.findById(dealershipID).get();
        foundDealership.setName(dealership.getName());
        foundDealership.setAddress(dealership.getAddress());
        foundDealership.setCapacity(dealership.getCapacity());
        foundDealership.setReputation(dealership.getReputation());
        foundDealership.setHasService(dealership.isHasService());
        foundDealership.setOffersTradeIn(dealership.isOffersTradeIn());
        foundDealership.setCars(dealership.getCars());
        return this.dealershipRepository.save(foundDealership);
    }

    @Override
    public List<DealershipStatisticDTO> fetchStatisticForDealershipsInventories() {
        List<DealershipStatisticDTO> dealershipStatisticDTOS = new ArrayList<>();
        List<Dealership> dealershipList = this.dealershipRepository.findAll();
        for(Dealership dealership : dealershipList){
            double avg_value = 0;
            int size = 0;
            for(Car car: dealership.getCars()){
                size += 1;
                avg_value += car.getCarRetailPrice();
            }
            avg_value = avg_value/size;

            DealershipDTO dealershipDTO = new DealershipDTO();
            dealershipDTO.setId(dealership.getDealershipID());
            dealershipDTO.setName(dealership.getName());
            dealershipDTO.setReputation(dealership.getReputation());
            dealershipDTO.setCapacity(dealership.getCapacity());
            dealershipDTO.setHasService(dealership.isHasService());
            dealershipDTO.setOffersTradeIn(dealership.isOffersTradeIn());
            dealershipDTO.setAddress(dealership.getAddress());

            dealershipStatisticDTOS.add(new DealershipStatisticDTO(avg_value, dealershipDTO));
        }

        for(int i = 0; i<dealershipStatisticDTOS.size()-1; i++){
            for(int j = i+1; j<dealershipStatisticDTOS.size(); j++){
                if(dealershipStatisticDTOS.get(i).getAvgInventoryValue() < dealershipStatisticDTOS.get(j).getAvgInventoryValue()){
                    DealershipStatisticDTO statisticDTO = dealershipStatisticDTOS.get(i);
                    dealershipStatisticDTOS.set(i, dealershipStatisticDTOS.get(j));
                    dealershipStatisticDTOS.set(j, statisticDTO);
                }
            }
        }

        return dealershipStatisticDTOS;
    }

    @Override
    public void deleteDealership(Long dealershipID) {
        this.dealershipRepository.deleteById(dealershipID);
    }
}

