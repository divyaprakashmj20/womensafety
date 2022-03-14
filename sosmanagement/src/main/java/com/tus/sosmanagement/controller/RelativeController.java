package com.tus.sosmanagement.controller;

import com.tus.sosmanagement.entity.SosEntity;
import com.tus.sosmanagement.service.RelativeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;

@RestController
public class RelativeController {


    @Autowired
    RelativeService relativeService;

    @GetMapping("/relatives/sos/{relative_id}")
    public ArrayList<SosEntity> getRelativesInDanger(@PathVariable Long relative_id){
        return relativeService.getRelativesInDanger(relative_id);
    }



}
