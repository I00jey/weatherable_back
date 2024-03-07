package com.weatherable.weatherable.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Table(name = "board_image")
@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
public class LookbookImageEntity {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(length = 10000)
    private String image_path;

    @ManyToOne
    @JoinColumn(name = "lookbook_id", nullable = false)
    private LookbookEntity lookbookImage;

}
