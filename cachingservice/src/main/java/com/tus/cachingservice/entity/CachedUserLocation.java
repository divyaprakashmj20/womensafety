package com.tus.cachingservice.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CachedUserLocation {

    @Id
    private Long user_id;
    private Double latitude;
    private Double longitude;

}
