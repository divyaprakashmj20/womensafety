package com.tus.notificationservice.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Embeddable;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import java.io.Serializable;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class UserSosLocationData {
    @EmbeddedId
    private SosUserId sosUserId;

    private Double latitude;
    private Double longitude;

//    public UserSosLocationData(SosUserId sosUserId, Double latitude, Double longitude) {
//        this.sosUserId = sosUserId;
//        this.latitude = latitude;
//        this.longitude = longitude;
//    }

    @Embeddable
    @Data
    @NoArgsConstructor
    public static class SosUserId implements Serializable {
        private Long sosId;
        private Long userId;

        public SosUserId(Long sos_id, Long userId) {
            this.sosId = sos_id;
            this.userId = userId;
        }

    }

}
