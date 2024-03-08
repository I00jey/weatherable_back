package com.weatherable.weatherable.Entity;

import com.weatherable.weatherable.Model.ClothInfoEntity;
import com.weatherable.weatherable.enums.*;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;


import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@Table(name = "closet")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
public class ClosetEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private UserEntity userCloset;

    private MajorCategory majorCategory;

    private MiddleCategory middleCategory;

    private long price;

    private Color color;

    private Size size;

    private Thickness thickness;

    private String productName;

    private String brand;

    @Column(name = "small_image_path")
    private String smallImagePath;

    @Column(name = "big_image_path")
    private String bigImagePath;

    private Style style;

    private Season season;


    @CreationTimestamp
    @Column(nullable = false, name = "created_at")
    private Timestamp createdAt;



}
