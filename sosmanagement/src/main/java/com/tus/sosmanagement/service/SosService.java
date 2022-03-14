package com.tus.sosmanagement.service;


import com.tus.sosmanagement.dto.SosRegisterDTO;
import com.tus.sosmanagement.dto.SosUpdateDTO;
import com.tus.sosmanagement.entity.SosEntity;
import com.tus.sosmanagement.enums.SosState;
import com.tus.sosmanagement.repository.SosRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class SosService {

    @Autowired
    SosRepository sosRepository;

    @Autowired
    MicroServiceExecutorService microServiceExecutorService;

    public SosEntity registerSos(SosRegisterDTO sosRegisterDTO){
        SosEntity sosEntity = new SosEntity();
        BeanUtils.copyProperties(sosRegisterDTO, sosEntity);
        sosEntity.setSosRegistrationTime(LocalDateTime.now());
        sosEntity.setSosState(SosState.ACTIVE);
        sosEntity.setSosLastUpdateTime(LocalDateTime.now());
        SosEntity savedSosEntity = sosRepository.save(sosEntity);
        microServiceExecutorService.getRelatives(savedSosEntity);
        //notify relative users
        return savedSosEntity;
    }

    public SosEntity getActiveSos(Long id) {
        return sosRepository.getByUserIdAndSosState(id,SosState.ACTIVE);
    }

    public SosEntity updateSosDetails(SosUpdateDTO sosUpdateDTO) {
        Optional<SosEntity> sosEntityOptional = sosRepository.findById(sosUpdateDTO.getSosId());
        SosEntity sosEntity = sosEntityOptional.get();
        sosEntity.setLatitude(sosUpdateDTO.getLatitude());
        sosEntity.setLongitude(sosUpdateDTO.getLongitude());
        sosEntity.setSosLastUpdateTime(LocalDateTime.now());
        return sosRepository.save(sosEntity);
    }
}
