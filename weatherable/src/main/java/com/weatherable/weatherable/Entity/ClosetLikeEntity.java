package com.weatherable.weatherable.Entity;

import jakarta.persistence.*;
import lombok.*;

@Table(name = "follow")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Entity
public class ClosetLikeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private UserEntity fromUser;


    @ManyToOne
    @JoinColumn(name = "closet_id", nullable = false)
    private UserEntity userClosetLike;
}
