package com.weatherable.weatherable.Model;

import com.weatherable.weatherable.enums.*;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;

@Document(collection = "cloth_info")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class ClothInfoEntity {
    @Id
    private String id;

    private MajorCategory majorCategory;

    private MiddleCategory middleCategory;

    private Color color;

    private Size size;

    private Thickness thickness;

    private Season season;

    @Column(name = "product_name")
    private String productName;

    private String brand;

    @Column(name = "small_image_path")
    private String smallImagePath;


    @Column(name = "big_image_path")
    private String bigImagePath;


}
