package com.weatherable.weatherable.Entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@Table(name = "codi")
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Entity
public class CodiEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private UserEntity userCodi;

    private Long topIndex;
    private Long bottomIndex;
    private Long outerIndex;
    private Long shoesIndex;
    private Long accessoryIndex;
    private Long capIndex;

    @Column(nullable = false, columnDefinition = "BOOLEAN DEFAULT true")
    private boolean active;

    @Column(nullable = false, columnDefinition = "BOOLEAN DEFAULT false")
    private boolean showing;


    @CreationTimestamp
    @Column(nullable = false, name = "created_at")
    private Timestamp createdAt;

    @OneToMany(mappedBy = "codiComment", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<CommentEntity> codiComments = new ArrayList<>();

    @OneToMany(mappedBy = "codiIndex", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<codiLikeEntity> codiLikes = new ArrayList<>();

}
