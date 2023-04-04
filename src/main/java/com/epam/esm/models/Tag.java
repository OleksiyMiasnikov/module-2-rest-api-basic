package com.epam.esm.models;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Tag {
    private int id;
    private String name;
}
