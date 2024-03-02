package com.weatherable.weatherable.Model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ClothInfo {
    private MajorCategory major;
    private MiddelCategory middle;
    private Description description;
}
