package com.weatherable.weatherable.Entity;

import com.weatherable.weatherable.Entity.enums.ClothInfo;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;

@Document(collection = "closet")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class ClosetEntity {

    @Id
    private String id;
    private String userid;
    private ArrayList<ClothInfo> clothInfo;

    // test 용 main 함수. 옷장에 넣을 때 이러한 구조로 넣게 될 듯 합니다.
}
