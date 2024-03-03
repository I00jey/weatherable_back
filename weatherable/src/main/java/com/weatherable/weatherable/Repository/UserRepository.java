package com.weatherable.weatherable.Repository;

import com.weatherable.weatherable.Model.ClosetModel;
import com.weatherable.weatherable.Model.UserModel;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends MongoRepository<UserModel, String> {
    UserModel findUserModelByUserid(String userid);
}
