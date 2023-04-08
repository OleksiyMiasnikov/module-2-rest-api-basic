package com.epam.esm.models;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Tag {
    private int id;
    @NotEmpty(message = "Should not be empty")
    @Size(min=2, max=40, message = "Should be more then 2 and less then 40 symbols")
    private String name;
}
