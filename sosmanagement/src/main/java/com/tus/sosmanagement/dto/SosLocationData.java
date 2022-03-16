package com.tus.sosmanagement.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SosLocationData {
    Long sos_id;
    Double latitude;
    Double longitude;
}
