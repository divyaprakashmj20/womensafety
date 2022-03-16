package com.tus.cachingservice.VO;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class NearbyUsersData {
    private Long sos_id;
    private Long user_id;
    private Double latitude;
    private Double longitude;
}
