package com.weatherable.weatherable.DTO;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserDTO {
    private long id;
    private String userid;
    private String password;
    private String nickname;
    private String image_path;
    private Double height;
    private Double weight;
}
