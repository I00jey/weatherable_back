package com.weatherable.weatherable.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.catalina.User;
import org.hibernate.annotations.CreationTimestamp;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@Table(name = "board")
@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
public class BoardEntity {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private UserEntity userBoard;

    @Column(nullable = false, length = 50)
    private String title;

    @Column(columnDefinition = "Text", nullable = false)
    private String content;


    @CreationTimestamp
    private Timestamp createdAt;

    @OneToMany(mappedBy = "board", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<CommentEntity> comments = new ArrayList<>();

    @OneToMany(mappedBy = "boardImage", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<BoardImageEntity> boardImage = new ArrayList<>();
}
