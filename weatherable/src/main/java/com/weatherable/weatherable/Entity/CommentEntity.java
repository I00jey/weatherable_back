package com.weatherable.weatherable.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import java.sql.Timestamp;

@Table(name = "comment")
@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
public class CommentEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne
    @JoinColumn(name = "boardentity_id")
    private BoardEntity boardEntity;

    @Column(nullable = false)
    private String userid;

    @Column(nullable = false)
    private String content;

    @CreationTimestamp
    private Timestamp createdAt;

}
