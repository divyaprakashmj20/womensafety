package com.tus.cachingservice.repository;

import com.tus.cachingservice.dto.UserLocationData;
import com.tus.cachingservice.entity.CachedUserLocation;
import com.tus.cachingservice.service.CachedUserLocationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@Repository
public interface CachedUserLocationRepository extends JpaRepository<CachedUserLocation,Long> {

}
