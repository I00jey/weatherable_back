package com.weatherable.weatherable.Model;

import com.weatherable.weatherable.enums.*;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;


@Document(collection = "clothes")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class ClothInfoEntity {
    @Id
    @Column(name = "_id")
    private String id;

    @Column(name = "major_category")
    private MajorCategory majorCategory;

    @Column(name = "middle_category")
    private MiddleCategory middleCategory;

    private long price;

    private Thickness thickness;

    private Season season;

    @Column(name = "product_name")
    private String productName;

    private String brand;

    @Column(name = "small_img")
    private String smallImagePath;

    @Column(name = "big_img")
    private String bigImagePath;

}
