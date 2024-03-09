package com.weatherable.weatherable.Entity;

import com.weatherable.weatherable.enums.Style;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@Table(name = "user")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false)
    private String userid;

    @Column(nullable = false, length = 1000)
    private String password;

    @Column(nullable = false)
    private String nickname;

    @Column(length = 10000)
    private String image_path;

    @Column(columnDefinition = "Text")
    private String introduction;

    @CreationTimestamp
    @Column(nullable = false, name = "created_at")
    private Timestamp createdAt;

    private Double height;
    private Double weight;

    private Style favoriteStyle;

    @OneToMany(mappedBy = "userLookbook", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<LookbookEntity> Lookbook = new ArrayList<>();

    @OneToMany(mappedBy = "userComment", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<CommentEntity> comment = new ArrayList<>();

    @OneToMany(mappedBy = "userCloset", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ClosetEntity> closet = new ArrayList<>();

    @OneToMany(mappedBy = "fromUser", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ClosetLikeEntity> fromUser = new ArrayList<>();

    @OneToMany(mappedBy = "userClosetLike", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ClosetLikeEntity> toUser = new ArrayList<>();

    @OneToMany(mappedBy = "userIndex", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<LookbookLikeEntity> userLookBookLikes = new ArrayList<>();


}
