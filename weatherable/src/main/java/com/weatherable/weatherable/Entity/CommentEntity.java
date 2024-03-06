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
    @JoinColumn(name = "board_id", nullable = false)
    private BoardEntity board;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private UserEntity userComment;

    @Column(nullable = false)
    private String content;

    @CreationTimestamp
    private Timestamp createdAt;

}
