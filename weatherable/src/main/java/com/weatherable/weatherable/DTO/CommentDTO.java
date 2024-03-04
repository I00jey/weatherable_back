package com.weatherable.weatherable.DTO;

import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.CreatedDate;

import java.util.Date;

@Data
@Builder
public class CommentDTO {
    private String id;
    private String postId;
    private String userid;
    private String content;
    private Date createdAt;
}
