package com.tus.sosmanagement.dto;

import lombok.Data;

@Data
public class SosUpdateDTO {

    Long sosId;
    Long userId;
    Double latitude;
    Double longitude;
}
