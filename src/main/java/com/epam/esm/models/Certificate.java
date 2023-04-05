package com.epam.esm.models;

import lombok.Builder;
import lombok.Data;

import java.text.SimpleDateFormat;

@Data
@Builder
public class Certificate {
    private int id;
    private String name;
    private String description;
    private double price;
    private int duration;
    private SimpleDateFormat createDate;
    private SimpleDateFormat lastUpdateDate;
}
