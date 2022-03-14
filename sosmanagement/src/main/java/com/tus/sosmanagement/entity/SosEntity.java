package com.tus.sosmanagement.entity;

import com.tus.sosmanagement.enums.SosState;
import lombok.Data;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Data
public class SosEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long sosId;

    Long userId;

    Double latitude;

    Double longitude;

    LocalDateTime sosRegistrationTime;

    LocalDateTime sosLastUpdateTime;

    @Enumerated(EnumType.STRING)
    SosState sosState;



}
