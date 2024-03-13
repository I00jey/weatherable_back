package com.weatherable.weatherable.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.weatherable.weatherable.enums.Style;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.security.core.userdetails.UserDetails;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@Table(name = "user")
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
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

    @Column(name = "favorite_style")
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
