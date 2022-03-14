package com.tus.womensafety.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

@Data
@Entity
@NoArgsConstructor
public class RelativeEntity {

    @JsonIgnore
    @EmbeddedId
    private Key key = new Key();

    public RelativeEntity(UserEntity userEntity, UserEntity relative) {
        this.userEntity = userEntity;
        this.relative = relative;
//        key.userId = user.getId();
//        key.friendId =friend.getId();
    }

    @Override
    public String toString() {
        return "Friend{" +
                "key=" + key +
                ", user=" + userEntity +
                ", friend=" + relative +
                '}';
    }

    @ManyToOne
    @MapsId("userId")
    private UserEntity userEntity;

    @ManyToOne
    @MapsId("relativeId")
    private UserEntity relative;

    @Embeddable
    @Data
    public static class Key implements Serializable {
        private Long userId;
        private Long relativeId;
    }
}