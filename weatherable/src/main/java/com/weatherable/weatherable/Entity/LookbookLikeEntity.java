package com.weatherable.weatherable.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Table(name = "lookbook_like")
@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
public class LookbookLikeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private UserEntity userIndex;


    @ManyToOne
    @JoinColumn(name = "lookbook_id", nullable = false)
    private LookbookEntity postIndex;
}