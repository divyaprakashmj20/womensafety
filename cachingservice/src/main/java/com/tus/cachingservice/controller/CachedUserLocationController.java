package com.tus.cachingservice.controller;

import com.tus.cachingservice.dto.SosLocationData;
import com.tus.cachingservice.dto.UserLocationData;
import com.tus.cachingservice.service.CachedUserLocationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CachedUserLocationController {

    @Autowired
    CachedUserLocationService cachedUserLocationService;

    @PostMapping("/user/location")
    public void updateUserLocation(@RequestBody UserLocationData userLocationData){
        cachedUserLocationService.updateUserLocation(userLocationData);
    }

    @PostMapping("/sos/location")
    public void sendUserLocationToNotification(@RequestBody SosLocationData sosLocationData){
        cachedUserLocationService.sendUserLocationToNotification(sosLocationData);
    }

}
