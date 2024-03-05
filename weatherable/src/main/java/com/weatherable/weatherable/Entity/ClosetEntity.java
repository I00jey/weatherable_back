package com.weatherable.weatherable.Entity;

import com.weatherable.weatherable.Entity.enums.ClothInfo;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@Table(name = "closet")
@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
public class ClosetEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false, length = 20)
    private String userid;

    @ManyToOne
    @JoinColumn(name = "clothinfoentity_id")
    private ClothInfoEntity clothInfoEntity;

    @CreationTimestamp
    private Timestamp createdAt;



}
