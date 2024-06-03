package com.example.jasperreports.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Nutrition {
    private String nutrition;
    private String metric;
    private int total;
    private int goal;
}
