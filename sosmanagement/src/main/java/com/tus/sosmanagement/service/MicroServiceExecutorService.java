package com.tus.sosmanagement.service;

import com.tus.sosmanagement.VO.RelativeVO;
import com.tus.sosmanagement.dto.SosRegisterDTO;
import com.tus.sosmanagement.entity.RelativeEntity;
import com.tus.sosmanagement.entity.SosEntity;
import com.tus.sosmanagement.repository.RelativeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.concurrent.CompletableFuture;

@Service
public class MicroServiceExecutorService {

    @Autowired
    RelativeRepository relativeRepository;

    @Async
    public CompletableFuture<RelativeVO> getRelatives(SosEntity savedSosEntity){
        RestTemplate restTemplate = new RestTemplate();
        System.out.println(Thread.currentThread().getName());
        String userServiceURL = "http://localhost:8081/user/" + savedSosEntity.getUserId();
        RelativeVO relativeVO = restTemplate.getForObject(userServiceURL, RelativeVO.class);
        System.out.println(relativeVO);
        for(RelativeVO.Relative relative : relativeVO.getResponse().getRelatives()){
            RelativeEntity relativeEntity = new RelativeEntity();
            relativeEntity.setRelativeEntityId(new RelativeEntity.RelativeEntityId(savedSosEntity.getSosId(), relative.getId()));
            relativeRepository.save(relativeEntity);
        }
        return CompletableFuture.completedFuture(relativeVO);
    }
}
