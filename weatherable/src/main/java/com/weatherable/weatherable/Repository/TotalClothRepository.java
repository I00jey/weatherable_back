package com.weatherable.weatherable.Repository;

import com.weatherable.weatherable.Entity.ClosetEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TotalClothRepository extends JpaRepository<ClosetEntity, String> {
    List<ClosetEntity> findClosetModelByUserid(String userid);
}
