package com.weatherable.weatherable.Entity;

import com.weatherable.weatherable.Entity.enums.ClothInfo;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Document(collection = "total_cloth")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class TotalClothEntity {
    @Id
    private String id;
    private ClothInfo clothInfo;
}
