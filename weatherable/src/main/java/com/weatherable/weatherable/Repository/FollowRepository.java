package com.weatherable.weatherable.Repository;

import com.weatherable.weatherable.Entity.ClosetEntity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FollowRepository extends MongoRepository<ClosetEntity, String> {
    List<ClosetEntity> findClosetModelByUserid(String userid);
}
