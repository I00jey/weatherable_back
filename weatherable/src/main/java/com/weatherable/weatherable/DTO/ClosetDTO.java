package com.weatherable.weatherable.DTO;

import com.weatherable.weatherable.Entity.enums.ClothInfo;
import lombok.Builder;
import lombok.Data;

import java.util.ArrayList;

@Data
@Builder
public class ClosetDTO {
    private String id;
    private String userid;
    private ArrayList<ClothInfo> clothInfo;
}
