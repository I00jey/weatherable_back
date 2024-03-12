package com.weatherable.weatherable.Entity;

import jakarta.persistence.*;
import lombok.*;

@Table(name = "board_image")
@NoArgsConstructor
@AllArgsConstructor
@Getter
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
