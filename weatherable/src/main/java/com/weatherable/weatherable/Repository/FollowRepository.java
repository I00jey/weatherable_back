package com.weatherable.weatherable.Repository;

import com.weatherable.weatherable.Entity.ClosetLikeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FollowRepository extends JpaRepository<ClosetLikeEntity, Long> {

}
