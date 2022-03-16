package com.tus.sosmanagement.controller;

import com.tus.sosmanagement.dto.NearbyUsersData;
import com.tus.sosmanagement.dto.SosRegisterDTO;
import com.tus.sosmanagement.dto.SosUpdateDTO;
import com.tus.sosmanagement.entity.SosEntity;
import com.tus.sosmanagement.service.SosService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class SosController {

    @Autowired
    SosService sosService;


    @PostMapping("/sos")
    public ResponseEntity<?> registerSos(@RequestBody SosRegisterDTO sosRegisterDTO){
        SosEntity sosEntity = sosService.registerSos(sosRegisterDTO);
        return new ResponseEntity<>(sosEntity, HttpStatus.OK);
    }

    @GetMapping("/sos/{id}")
    public ResponseEntity<?> getActiveSos(@PathVariable Long id){
        SosEntity sosEntity = sosService.getActiveSos(id);
        return new ResponseEntity<>(sosEntity, HttpStatus.OK);
    }

    @PutMapping("/sos/location")
    public ResponseEntity<?> updateSosDetails(@RequestBody SosUpdateDTO sosUpdateDTO){
        SosEntity sosEntity = sosService.updateSosDetails(sosUpdateDTO);
        return new ResponseEntity<>(sosEntity, HttpStatus.OK);
    }

    @PostMapping("/sos/nearby")
    public String associateSosWithUser(@RequestBody List<NearbyUsersData> nearbyUsersData){
        sosService.associateSosWithUser();
        return "Success";
    }

}
