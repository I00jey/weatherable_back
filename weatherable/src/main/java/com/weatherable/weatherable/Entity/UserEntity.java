package com.weatherable.weatherable.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Table(name = "user")
@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false)
    private String userid;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private String nickname;

    private String image_path;

    private Double height;
    private Double weight;
}
