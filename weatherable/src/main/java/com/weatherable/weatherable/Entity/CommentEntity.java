package com.weatherable.weatherable.Entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Data
@NoArgsConstructor
@Document(collection = "comment")
@AllArgsConstructor
public class CommentEntity {
    @Id
    private String id;
    private String postId;
    private String userid;
    private String content;
    @CreatedDate
    private Date createdAt;
}
