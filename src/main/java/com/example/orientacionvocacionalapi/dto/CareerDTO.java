package com.example.orientacionvocacionalapi.dto;

import com.example.orientacionvocacionalapi.model.entity.Location;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CareerDTO {
    private Long id;
    private String name;
    private String description;
    private String priceMonthly;
    private String img;
    private Location location;
    private String AreaName;
}