package com.weatherable.weatherable.DTO;

import lombok.Builder;
import lombok.Data;

import java.sql.Timestamp;

@Data
@Builder
public class LookBookDTO {
    private long id;
    private String userid;
    private String title;
    private String content;
    private String image_path;
    private Timestamp createdAt;
}
