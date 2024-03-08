package com.weatherable.weatherable.Repository;

import com.weatherable.weatherable.Entity.LookbookEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LookbookRepository extends JpaRepository<LookbookEntity, Long> {

}
