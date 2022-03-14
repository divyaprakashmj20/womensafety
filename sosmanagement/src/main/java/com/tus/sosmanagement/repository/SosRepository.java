package com.tus.sosmanagement.repository;

import com.tus.sosmanagement.entity.SosEntity;
import com.tus.sosmanagement.enums.SosState;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SosRepository extends JpaRepository<SosEntity, Long> {
    SosEntity getByUserIdAndSosState(Long id, SosState active);
}
