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

    @ManyToOne
    @JoinColumn(name = "from_user", nullable = false)
    private UserEntity fromUser;


    @ManyToOne
    @JoinColumn(name = "to_user", nullable = false)
    private UserEntity toUser;
}
