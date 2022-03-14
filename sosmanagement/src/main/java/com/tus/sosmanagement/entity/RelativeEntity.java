package com.tus.sosmanagement.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Embeddable;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import java.io.Serializable;

@Data
@Entity
@NoArgsConstructor
public class RelativeEntity {
    @EmbeddedId
    private RelativeEntityId relativeEntityId;


    @Embeddable
    @Data
    @NoArgsConstructor
    public static class RelativeEntityId implements Serializable {
        private Long sosId;
        private Long relativeId;

        public RelativeEntityId(Long sos_id, Long relative_id) {
            this.sosId = sos_id;
            this.relativeId = relative_id;
        }

    }

}


