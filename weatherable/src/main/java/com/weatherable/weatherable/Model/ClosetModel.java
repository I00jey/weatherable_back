package com.weatherable.weatherable.Model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
@NoArgsConstructor
@AllArgsConstructor
@Data
public class ClosetModel {

    @Id
    private String id;
    private String userid;
    private ClothInfo clothInfo;

    // test 용 main 함수. 옷장에 넣을 때 이러한 구조로 넣게 될 듯 합니다.
    public static void main(String[] args) {
        ClosetModel closetModel = new ClosetModel("id", "userid",
                new ClothInfo(MajorCategory.Top, TopMiddleCategory.Sweat_Shirt,
                        new Description("product", "brand", "image")));
        System.out.println(closetModel);
    }
}
