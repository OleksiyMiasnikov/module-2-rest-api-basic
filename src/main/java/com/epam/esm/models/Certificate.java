package com.epam.esm.models;

import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import lombok.Builder;
import lombok.Data;

import java.text.SimpleDateFormat;

@Data
@Builder
public class Certificate {
    private int id;
    @NotEmpty(message = "Should not be empty")
    private String name;
    private String description;
    @Min(value = 0, message = "Should be positive")
    @Digits(integer = 10 /*precision*/, fraction = 2 /*scale*/)
    private double price;
    @Min(value = 0, message = "Should be positive")
    @Digits(integer = 10 /*precision*/, fraction = 0 /*scale*/)
    private int duration;

    private SimpleDateFormat createDate;
    private SimpleDateFormat lastUpdateDate;
}
