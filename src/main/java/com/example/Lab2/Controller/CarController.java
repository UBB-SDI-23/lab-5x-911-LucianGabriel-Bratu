package com.example.Lab2.Controller;

import com.example.Lab2.Model.Car;
import com.example.Lab2.Model.DTOs.CarDealershipDTO;
import com.example.Lab2.Model.DTOs.CarDealershipIDDTO;
import com.example.Lab2.Service.CarService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class CarController {
    @Autowired
    private CarService carService;

    @PostMapping("/dealerships/{dealershipID}/cars")
    public Car saveCar(@PathVariable("dealershipID") Long dealershipID, @Valid @RequestBody Car car) {
        return this.carService.saveCar(dealershipID, car);
    }

    @GetMapping("/dealerships/{dealershipID}/cars")
    public List<Car> fetchCarListForDealershipID(@PathVariable("dealershipID") Long dealershipID) {
        return this.carService.fetchCarListForDealershipID(dealershipID);
    }

    @GetMapping("/dealerships/{dealershipID}/cars/{carID}")
    public Car one(@PathVariable("dealershipID") Long dealershipID, @PathVariable("carID") Long carID) {
        return this.carService.one(dealershipID, carID);
    }

    @GetMapping("/cars")
    public List<CarDealershipIDDTO> fetchCars() {
        return this.carService.getAllCars();
    }

    @GetMapping("/cars/{carID}")
    public CarDealershipDTO fetchCarByIdWithDealershipDTO(@PathVariable("carID") Long carID) {
        return this.carService.getOneCarWithDealershipObject(carID);
    }

    @PutMapping("/dealerships/{dealershipID}/cars/{carID}")
    public Car updateCar(@Valid @RequestBody Car car, @PathVariable("dealershipID") Long dealershipID, @PathVariable("carID") Long carID) {
        return this.carService.updateCar(car, dealershipID, carID);
    }

    @DeleteMapping("/dealerships/{dealershipID}/cars/{id}")
    public String deleteCar(@PathVariable("dealershipID") Long dealershipID, @PathVariable("id") Long carID) {
        this.carService.deleteCar(dealershipID, carID);
        return "Car successfully deleted";
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