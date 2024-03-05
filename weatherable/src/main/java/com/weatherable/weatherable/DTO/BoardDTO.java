package com.weatherable.weatherable.DTO;

import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.CreatedDate;

import java.sql.Timestamp;
import java.util.Date;

@Data
@Builder
public class BoardDTO {
    private long id;
    private String userid;
    private String title;
    private String content;
    private String image_path;
    private Timestamp createdAt;
}
