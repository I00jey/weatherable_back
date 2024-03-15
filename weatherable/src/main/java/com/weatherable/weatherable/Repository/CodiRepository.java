package com.weatherable.weatherable.Repository;

import com.weatherable.weatherable.Entity.CodiEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CodiRepository extends JpaRepository<CodiEntity, Long> {

    @Modifying
    @Query(value = "update codi set active = false where id = :id", nativeQuery = true)
    void deleteCodi(Long id);

    Optional<List<CodiEntity>> findByUserCodiIdAndAccess(Long id, boolean access);

    Optional<CodiEntity> getByIdAndAccess(Long id, boolean access);


}
