package com.weatherable.weatherable.Entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Document(collection = "board")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class BoardEntity {

    @Id
    private String id;
    private String userid;
    private String title;
    private String content;
    private String image_path;
    @CreatedDate
    private Date createdAt;
}
