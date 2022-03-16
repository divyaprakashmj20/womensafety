package com.tus.cachingservice.service;

import com.tus.cachingservice.VO.NearbyUsersData;
import com.tus.cachingservice.dto.SosLocationData;
import com.tus.cachingservice.dto.UserLocationData;
import com.tus.cachingservice.entity.CachedUserLocation;
import com.tus.cachingservice.repository.CachedUserLocationRepository;
import com.tus.cachingservice.utils.DistanceCalculator;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CachedUserLocationService {

    @Autowired
    CachedUserLocationRepository cachedUserLocationRepository;

    @Value("${application.thresholddistance}")
    private Integer thresholdDistance;

    public void updateUserLocation(UserLocationData userLocationData) {
        Optional<CachedUserLocation> cachedUserLocationOptional = cachedUserLocationRepository.findById(userLocationData.getUser_id());
        if(cachedUserLocationOptional.isPresent()){
            CachedUserLocation cachedUserLocation = cachedUserLocationOptional.get();
            cachedUserLocation.setLatitude(userLocationData.getLatitude());
            cachedUserLocation.setLongitude(userLocationData.getLongitude());
            cachedUserLocationRepository.save(cachedUserLocation);
        }else{
            CachedUserLocation cachedUserLocation = new CachedUserLocation();
            BeanUtils.copyProperties(userLocationData,cachedUserLocation);
            cachedUserLocationRepository.save(cachedUserLocation);
        }

    }

    public void sendUserLocationToNotification(SosLocationData sosLocationData) {
        ArrayList<NearbyUsersData> nearbyUsersDataArrayList = new ArrayList<>();
        List<CachedUserLocation> cachedUserLocationList = cachedUserLocationRepository.findAll();

        for(CachedUserLocation cachedUserLocation : cachedUserLocationList){
            System.out.println(DistanceCalculator.distance(cachedUserLocation.getLatitude(),sosLocationData.getLatitude(),cachedUserLocation.getLongitude(),sosLocationData.getLongitude()));
            if(DistanceCalculator.distance(cachedUserLocation.getLatitude(),sosLocationData.getLatitude(),cachedUserLocation.getLongitude(),sosLocationData.getLongitude()) < thresholdDistance){
                nearbyUsersDataArrayList.add(new NearbyUsersData(sosLocationData.getSos_id(),cachedUserLocation.getUser_id(),sosLocationData.getLatitude(),sosLocationData.getLongitude()));
            }
        }

        RestTemplate restTemplate = new RestTemplate();
        String notificationAddNearbyUserAndSosListList = "http://localhost:8085/sos/users";
        restTemplate.postForEntity(notificationAddNearbyUserAndSosListList, nearbyUsersDataArrayList, Object.class);

        System.out.println(nearbyUsersDataArrayList);
    }
}
