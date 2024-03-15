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

    @Modifying
    @Query(value = "update codi set showing = true where id = :id", nativeQuery = true)
    void showCodi(Long id);


    @Modifying
    @Query(value = "update codi set showing = false where id = :id", nativeQuery = true)
    void hideCodi(Long id);

    Optional<List<CodiEntity>> findByUserCodiIdAndActiveOrderByCreatedAtDesc(Long id, boolean access);
    Optional<List<CodiEntity>> findByUserCodiIdAndActiveAndShowingOrderByCreatedAtDesc(Long id, boolean access, boolean show);

    Optional<List<CodiEntity>> findByActiveOrderByCreatedAtDesc(boolean access);

    Optional<CodiEntity> getByIdAndActive(Long id, boolean access);

    Optional<CodiEntity> getByIdAndActiveAndShowing(Long id, boolean access, boolean show);


}
