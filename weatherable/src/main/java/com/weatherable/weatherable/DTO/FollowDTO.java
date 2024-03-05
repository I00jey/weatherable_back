package com.weatherable.weatherable.DTO;

import lombok.Builder;
import lombok.Data;

import java.util.ArrayList;

@Data
@Builder
public class FollowDTO {
    private long id;
    private String userid;
    private String followingId;
}
