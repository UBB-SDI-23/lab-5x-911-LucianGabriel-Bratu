package com.example.Lab2.Controller;

import com.example.Lab2.Model.Mechanic;
import com.example.Lab2.Service.MechanicService;
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
@CrossOrigin
public class MechanicController {
    @Autowired private MechanicService mechanicService;

    @GetMapping("/mechanics")
    public List<Mechanic> fetchAllMechanics(){
        return this.mechanicService.fetchMechanicList();
    }

    @GetMapping("/mechanics/{mechanicID}")
    public Mechanic one(@PathVariable("mechanicID") Long mechanicID){
        return this.mechanicService.one(mechanicID);
    }

    @PostMapping("/mechanics")
    public Mechanic saveMechanic(@Valid @RequestBody Mechanic mechanic){
        return this.mechanicService.saveMechanic(mechanic);
    }

    @PutMapping("/mechanics/{mechanicID}")
    public Mechanic updateMechanic(@Valid @RequestBody Mechanic mechanic, @PathVariable("mechanicID") Long mechanicID){
        return this.mechanicService.updateMechanic(mechanic, mechanicID);
    }

    @DeleteMapping("/mechanics/{mechanicID}")
    public String deleteMechanic(@PathVariable("mechanicID") Long mechanicID){
        this.mechanicService.deleteMechanic(mechanicID);
        return "Successfully deleted mechanic";
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
