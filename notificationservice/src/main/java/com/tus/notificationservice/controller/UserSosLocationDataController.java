package com.tus.notificationservice.controller;

import com.tus.notificationservice.dto.NearbyUsersData;
import com.tus.notificationservice.service.UserSosLocationDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class UserSosLocationDataController {

    @Autowired
    UserSosLocationDataService userSosLocationDataService;

    @PostMapping("/sos/users")
    public void updateUserSosLocationData(@RequestBody List<NearbyUsersData> nearbyUsersDataList){
        userSosLocationDataService.updateUserSosLocationData(nearbyUsersDataList);
    }

    @GetMapping("check/user/{id}")
    public List<NearbyUsersData> getSosAssociatedWithUser(@PathVariable Long id){
        return userSosLocationDataService.getSosAssociatedWithUser(id);
    }

    @PostMapping("/sos/nearby/user")
    public Object associateUserWithSosAsNearbyUser(@RequestBody ArrayList<NearbyUsersData> nearbyUsersDataList){
        return userSosLocationDataService.associateUserWithSosAsNearbyUser(nearbyUsersDataList);
    }



}
