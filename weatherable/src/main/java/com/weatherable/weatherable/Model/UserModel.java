package com.weatherable.weatherable.Model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
@NoArgsConstructor
@AllArgsConstructor
@Data
public class UserModel {

    @Id
    private String id;
    private String userid;
    private String password;
    private String username;
}
