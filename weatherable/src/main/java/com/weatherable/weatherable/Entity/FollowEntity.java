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
public class FollowEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false)
    private String userid;

    @Column(nullable = false)
    private String followingId;
}
