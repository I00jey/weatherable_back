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
@Builder
@Getter
@Entity
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false, unique = true)
    private String userid;

    @Column(nullable = false, length = 1000)
    private String password;

    @Column(nullable = false)
    private String nickname;

    @Column(length = 10000, name = "image_path")
    private String imagePath;

    @Column(columnDefinition = "Text")
    private String introduction;

    @CreationTimestamp
    @Column(nullable = false, name = "created_at")
    private Timestamp createdAt;

    private Double height;
    private Double weight;

    private String role = "USER";

    @Column(nullable = false, columnDefinition = "BOOLEAN DEFAULT true")
    private boolean active;

    @Column(name = "favorite_style")
    private Style favoriteStyle;


    @OneToMany(mappedBy = "userCodi", cascade = CascadeType.ALL, orphanRemoval = true)
    private final List<CodiEntity> userCodie = new ArrayList<>();

    @OneToMany(mappedBy = "userComment", cascade = CascadeType.ALL, orphanRemoval = true)
    private final List<CommentEntity> comment = new ArrayList<>();

    @OneToMany(mappedBy = "userCloset", cascade = CascadeType.ALL, orphanRemoval = true)
    private final List<ClosetEntity> closet = new ArrayList<>();

    @OneToMany(mappedBy = "fromUser", cascade = CascadeType.ALL, orphanRemoval = true)
    private final List<ClosetLikeEntity> fromUser = new ArrayList<>();

    @OneToMany(mappedBy = "userClosetLike", cascade = CascadeType.ALL, orphanRemoval = true)
    private final List<ClosetLikeEntity> toUser = new ArrayList<>();

    @OneToMany(mappedBy = "userIndex", cascade = CascadeType.ALL, orphanRemoval = true)
    private final List<codiLikeEntity> userLookBookLikes = new ArrayList<>();

    @OneToMany(mappedBy = "userEntity", cascade = CascadeType.ALL, orphanRemoval = true)
    private final List<UserSizeEntity> userSize = new ArrayList<>();



}
