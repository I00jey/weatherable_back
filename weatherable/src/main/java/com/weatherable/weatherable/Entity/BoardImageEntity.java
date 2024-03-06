package com.weatherable.weatherable.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@Table(name = "board_image")
@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
public class BoardImageEntity {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(length = 10000)
    private String image_path;

    @ManyToOne
    @JoinColumn(name = "board_id", nullable = false)
    private BoardEntity boardImage;


}
