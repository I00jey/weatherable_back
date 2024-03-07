package com.weatherable.weatherable.Repository;

import com.weatherable.weatherable.Model.ClothInfoEntity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClothInfoRepository extends MongoRepository<ClothInfoEntity, String> {

}
