package com.tus.cachingservice.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserLocationData {
    private Long user_id;
    private Double latitude;
    private Double longitude;
}
