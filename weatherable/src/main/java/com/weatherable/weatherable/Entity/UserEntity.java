package com.weatherable.weatherable.Entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "user")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class UserEntity {

    @Id
    private String id;
    private String userid;
    private String password;
    private String username;
    private String image_path;
}
