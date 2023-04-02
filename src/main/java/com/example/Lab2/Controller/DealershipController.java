package com.example.Lab2.Controller;

import com.example.Lab2.Model.DTOs.DealershipDTO;
import com.example.Lab2.Model.DTOs.DealershipStatisticDTO;
import com.example.Lab2.Model.Dealership;
import com.example.Lab2.Service.DealershipService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin
public class DealershipController {
    @Autowired
    private DealershipService dealershipService;

    @GetMapping("/dealerships")
    public List<DealershipDTO> fetchDealerships(){
        return this.dealershipService.fetchDealershipDTOList();
    }

    @GetMapping("/dealerships/{dealershipID}")
    public Dealership one(@PathVariable("dealershipID") Long dealershipID){
        return this.dealershipService.one(dealershipID);
    }

    @GetMapping("/dealerships/filter/{filterValue}")
    public List<Dealership> fetchDealershipsWithGreaterRating(@PathVariable("filterValue") Double filterValue){
        List<Dealership> dealershipList = new ArrayList<>();
        for(Dealership dealership : this.dealershipService.fetchDealershipList()){
            if(dealership.getReputation() > filterValue){
                dealershipList.add(dealership);
            }
        }
        return dealershipList;
    }

    @GetMapping("/dealerships/avgInventory")
    public List<DealershipStatisticDTO> fetchInventoryStatistic(){
        return this.dealershipService.fetchStatisticForDealershipsInventories();
    }

    @PostMapping("/dealerships")
    public Dealership saveDealership(@RequestBody @Valid Dealership dealership){
        return this.dealershipService.saveDealership(dealership);
    }

    @PutMapping("/dealerships/{dealershipID}")
    public Dealership updateDealership(@RequestBody @Valid Dealership dealership, @PathVariable("dealershipID") Long dealershipID){
        return this.dealershipService.updateDealership(dealership, dealershipID);
    }

    @DeleteMapping("/dealerships/{dealershipID}")
    public String deleteDealership(@PathVariable("dealershipID") Long dealershipID){
        this.dealershipService.deleteDealership(dealershipID);
        return "Dealership successfully deleted";
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String, String> handleValidationExceptions(
            MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        return errors;
    }
}
