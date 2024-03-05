package com.weatherable.weatherable.DTO;

import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.CreatedDate;

import java.sql.Timestamp;
import java.util.Date;

@Data
@Builder
public class CommentDTO {
    private long id;
    private String postId;
    private String userid;
    private String content;
    private Timestamp createdAt;
}
