package com.weatherable.weatherable.DTO;

import jakarta.persistence.Column;
import lombok.Builder;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import java.sql.Timestamp;
import java.util.ArrayList;

@Data
@Builder
public class ClosetDTO {
    private long id;
    private String userid;
    private String clothName;
    private Timestamp createdAt;
}
