package com.epam.esm.validators;

import com.epam.esm.exceptions.ModuleException;
import org.springframework.stereotype.Component;

@Component
public class SortingValidator  {
    public void validate(String sort){
        if (sort == null || sort.isEmpty()) return;
        if (!sort.equals("ASC") && !sort.equals("DESC")) {
            throw new ModuleException("Field 'sort_by' must be ASC or DESC", "40431");
        }
    }
}
