package com.tus.notificationservice.service;

import com.tus.notificationservice.dto.NearbyUsersData;
import com.tus.notificationservice.entity.UserSosLocationData;
import com.tus.notificationservice.repository.UserSosLocationDataRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserSosLocationDataService {

    @Autowired
    UserSosLocationDataRepo userSosLocationDataRepo;

    public void updateUserSosLocationData(List<NearbyUsersData> nearbyUsersDataList) {
        for(NearbyUsersData nearbyUsersData: nearbyUsersDataList){
            userSosLocationDataRepo.save(new UserSosLocationData(new UserSosLocationData.SosUserId(nearbyUsersData.getSos_id(), nearbyUsersData.getUser_id()),nearbyUsersData.getLatitude(),nearbyUsersData.getLongitude()));
        }
    }

    public List<NearbyUsersData> getSosAssociatedWithUser(Long id) {
        List<UserSosLocationData> userSosLocationDataList = userSosLocationDataRepo.getSosAssociatedWithUserById(id);
        ArrayList<NearbyUsersData> nearbyUsersDataArrayList = new ArrayList<>();
        for(UserSosLocationData userSosLocationData: userSosLocationDataList){
            nearbyUsersDataArrayList.add(new NearbyUsersData(userSosLocationData.getSosUserId().getSosId(), userSosLocationData.getSosUserId().getUserId(), userSosLocationData.getLatitude(), userSosLocationData.getLongitude()));
        }

        //delete the entriesss
        userSosLocationDataRepo.deleteAll(userSosLocationDataList);

        return nearbyUsersDataArrayList;
    }

    public Object associateUserWithSosAsNearbyUser(ArrayList<NearbyUsersData> nearbyUsersDataList) {
        RestTemplate restTemplate = new RestTemplate();
        System.out.println(nearbyUsersDataList);
        String notificationAddNearbyUserAndSosListUrl = "http://localhost:8083/sos/nearby";
        Object s = restTemplate.postForEntity(notificationAddNearbyUserAndSosListUrl, nearbyUsersDataList, String.class);
        return s;
    }
}
