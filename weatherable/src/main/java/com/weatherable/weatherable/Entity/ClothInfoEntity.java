package com.weatherable.weatherable.Entity;

import com.weatherable.weatherable.Entity.enums.*;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Table(name = "cloth_info")
@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
public class ClothInfoEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private MajorCategory majorCategory;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private MiddleCategory middleCategory;

    @Column(nullable = false)
    private long price;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Color color;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Size size;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Thickness thickness;

    @Column(nullable = false)
    private String productName;

    @Column(nullable = false)
    private String brand;

    @Column(nullable = false)
    private String image_Path;

    @OneToMany(mappedBy = "clothinfoentity", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ClosetEntity> closetEntityList = new ArrayList<>();
}
