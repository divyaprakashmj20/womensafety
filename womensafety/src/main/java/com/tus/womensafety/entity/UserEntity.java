package com.tus.womensafety.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity(name = "user_table")
//@JsonSerialize
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    private String name;

    @Column(unique = true)
    private String email;

    @Column(unique = true)
    private String phoneNumber;

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                '}';
    }

    @JsonIgnore
    @OneToMany(mappedBy="userEntity", cascade=CascadeType.ALL, orphanRemoval = true)
    private List<RelativeEntity> relatives = new ArrayList<>();
}
