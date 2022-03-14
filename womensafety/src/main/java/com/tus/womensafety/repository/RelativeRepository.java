package com.tus.womensafety.repository;

import com.tus.womensafety.entity.RelativeEntity;
import com.tus.womensafety.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RelativeRepository extends JpaRepository<RelativeEntity, RelativeEntity.Key> {

//    @Query(value = "SELECT * FROM friend f WHERE f.user_id = :uid", nativeQuery = true)
//    public List<Long> findRelativeOfId(@Param("uid") Long uid);
    public List<RelativeEntity> findByRelative(UserEntity u);
    public List<RelativeEntity> findByUserEntity(UserEntity u);
    public long deleteByUserEntity(UserEntity u);
    public long deleteByRelative(UserEntity u);
    public long deleteByUserEntityAndRelative(UserEntity u, UserEntity f);
//    public long saveByUserAndFriend(User u, User f);


}
