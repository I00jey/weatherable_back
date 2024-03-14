package com.weatherable.weatherable.Repository;

import com.weatherable.weatherable.Entity.CommentEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface CommentRepository extends JpaRepository<CommentEntity, Long> {

    @Modifying
    @Query(value = "update comment set active = false where id = :id", nativeQuery = true)
    void deleteComment(Long id);

}
