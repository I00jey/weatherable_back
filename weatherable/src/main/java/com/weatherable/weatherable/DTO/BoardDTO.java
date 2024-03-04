package com.weatherable.weatherable.DTO;

import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.CreatedDate;

import java.util.Date;

@Data
@Builder
public class BoardDTO {
    private String id;
    private String userid;
    private String title;
    private String content;
    private String image_path;
    private Date createdAt;
}
