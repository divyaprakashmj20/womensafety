package com.tus.sosmanagement.service;

import com.tus.sosmanagement.entity.SosEntity;
import com.tus.sosmanagement.repository.RelativeRepository;
import com.tus.sosmanagement.repository.SosRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class RelativeService {

    @Autowired
    RelativeRepository relativeRepository;

    @Autowired
    SosRepository sosRepository;

    public ArrayList<SosEntity> getRelativesInDanger(Long relative_id) {

        ArrayList<SosEntity> sosEntities = new ArrayList<>();
        for(Long sos_id: relativeRepository.findByRelativeId(relative_id)){
                sosEntities.add(sosRepository.findById(sos_id).get());
        }
        return sosEntities;
    }
}
