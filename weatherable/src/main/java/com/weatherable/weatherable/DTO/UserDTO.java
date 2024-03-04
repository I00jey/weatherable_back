package com.weatherable.weatherable.DTO;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserDTO {
    private String id;
    private String userid;
    private String password;
    private String username;
    private String image_path;
}
