package com.weatherable.weatherable.DTO;

import lombok.Builder;
import lombok.Data;

import java.util.ArrayList;

@Data
@Builder
public class FollowDTO {
    private String id;
    private String userid;
    private ArrayList<String> followingId;
}
