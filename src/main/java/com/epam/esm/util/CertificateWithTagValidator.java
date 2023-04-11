package com.epam.esm.util;

import com.epam.esm.models.Certificate;
import com.epam.esm.models.CertificateWithTag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CertificateWithTagValidator {
    @Autowired
    CertificateValidator certificateValidator;
    public void validate(CertificateWithTag certificateWithTag) {
        if ((certificateWithTag.getTag() == null) || (certificateWithTag.getTag().isBlank())) {
           throw new ModuleException("Field 'tag' can not be empty!", "40421");
        }
        //certificateValidator.validate(certificateWithTag);
    }
}
