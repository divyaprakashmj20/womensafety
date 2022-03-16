package com.tus.notificationservice.repository;

import com.tus.notificationservice.entity.UserSosLocationData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;

@Repository
public interface UserSosLocationDataRepo extends JpaRepository<UserSosLocationData, UserSosLocationData.SosUserId> {

    @Query(value = "SELECT * FROM user_sos_location_data u WHERE u.user_id = :user_id", nativeQuery = true)
    public ArrayList<UserSosLocationData> getSosAssociatedWithUserById(@Param("user_id") Long user_id);

}
