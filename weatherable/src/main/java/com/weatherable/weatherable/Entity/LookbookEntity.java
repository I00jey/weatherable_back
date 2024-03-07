package com.weatherable.weatherable.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@Table(name = "lookbook")
@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
public class LookbookEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private UserEntity userLookbook;

    @Column(nullable = false, length = 50)
    private String title;

    @Column(columnDefinition = "Text", nullable = false)
    private String content;


    @CreationTimestamp
    @Column(nullable = false, name = "created_at")
    private Timestamp createdAt;

    @OneToMany(mappedBy = "lookbookComment", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<CommentEntity> lookbookComments = new ArrayList<>();

    @OneToMany(mappedBy = "lookbookImage", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<LookbookImageEntity> lookbookImages = new ArrayList<>();

    @OneToMany(mappedBy = "postIndex", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<LookbookLikeEntity> lookbookLikes = new ArrayList<>();

}
