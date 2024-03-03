package com.weatherable.weatherable.Repository;

import com.weatherable.weatherable.Model.ClosetModel;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ClosetRepository extends MongoRepository<ClosetModel, String> {
    List<ClosetModel> findClosetModelByUserid(String userid);
}
