package com.weatherable.weatherable.DTO;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CodiDTO {
    private long id;
    private Long user_id;
    private String userid;
    private Long topIndex;
    private Long bottomIndex;
    private Long outerIndex;
    private Long shoesIndex;
    private Long accessoryIndex;
    private Long capIndex;

}
