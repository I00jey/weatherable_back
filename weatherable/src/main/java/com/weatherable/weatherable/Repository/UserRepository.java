package com.weatherable.weatherable.Repository;

import com.weatherable.weatherable.Entity.ClosetEntity;
import com.weatherable.weatherable.Entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {

    Optional<UserEntity> findByUserid(String userid);

    boolean existsByNicknameAndId(String nickname, Long id);

    @Modifying
    @Query(value = "update user set nickname = :nickname where id = :id", nativeQuery = true)
     void updateNickname(String nickname, Long id);


}
