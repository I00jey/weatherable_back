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

    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserEntity userCloset;

    @ManyToOne
    @JoinColumn(name = "cloth_info_id")
    private ClothInfoEntity clothInfo;

    @CreationTimestamp
    private Timestamp createdAt;



}
