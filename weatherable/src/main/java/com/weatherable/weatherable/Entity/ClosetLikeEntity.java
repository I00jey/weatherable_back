package com.weatherable.weatherable.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Table(name = "follow")
@NoArgsConstructor
@AllArgsConstructor
@Data
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
