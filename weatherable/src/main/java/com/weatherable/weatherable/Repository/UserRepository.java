package com.weatherable.weatherable.Repository;

import com.weatherable.weatherable.Entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, String> {
    UserEntity findUserEntityByUserid(String userid);



}
