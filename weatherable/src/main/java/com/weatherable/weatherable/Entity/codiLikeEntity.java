package com.weatherable.weatherable.Entity;

import jakarta.persistence.*;
import lombok.*;

@Table(name = "lookbook_like")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Entity
public class codiLikeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private UserEntity userIndex;


    @ManyToOne
    @JoinColumn(name = "codi_id", nullable = false)
    private CodiEntity codiIndex;
}