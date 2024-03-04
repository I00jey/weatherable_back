package com.weatherable.weatherable.Repository;

import com.weatherable.weatherable.Entity.UserEntity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends MongoRepository<UserEntity, String> {
    UserEntity findUserEntityByUserid(String userid);



}
