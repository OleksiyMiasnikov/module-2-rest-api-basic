package com.epam.esm.util;

import com.epam.esm.models.CertificateWithTag;
import org.springframework.stereotype.Component;

@Component
public class CertificateWithTagValidator {

    public void validate(CertificateWithTag certificateWithTag) {
        if ((certificateWithTag.getTag() == null) || (certificateWithTag.getTag().isEmpty())) {
           throw new ModuleException("Field 'tag' can not be empty!", "40421");
        }
    }
}
