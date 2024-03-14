package com.weatherable.weatherable.Repository;

import com.weatherable.weatherable.Entity.LookbookEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface LookbookRepository extends JpaRepository<LookbookEntity, Long> {

    @Modifying
    @Query(value = "update lookbook set active = false where id = :id", nativeQuery = true)
    void deletelookbook(Long id);

}
