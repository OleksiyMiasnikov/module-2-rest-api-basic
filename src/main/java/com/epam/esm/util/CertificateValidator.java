package com.epam.esm.util;

import com.epam.esm.models.Certificate;
import org.springframework.stereotype.Component;

@Component
public class CertificateValidator{

    public void validate(Certificate certificate) {
        StringBuilder allErrors = new StringBuilder();
        StringBuilder allCodes = new StringBuilder();
        if ((certificate.getName() == null) || (certificate.getName().isEmpty())) {
            allErrors.append("Field 'name' can not be empty! ; ");
            allCodes.append("40411 ; ");
        }
        if ((certificate.getDescription() == null) || (certificate.getDescription().isEmpty())) {
            allErrors.append("Field 'description' can not be empty! ; ");
            allCodes.append("40412 ; ");
        }
        if ((certificate.getPrice() == null) || (certificate.getPrice() <= 0)) {
            allErrors.append("Field 'price' should be more then 0! ; ");
            allCodes.append("40413 ; ");
        }
        if ((certificate.getDuration() == null) || (certificate.getDuration() <= 0)) {
            allErrors.append("Field 'duration' should be more then 0! ; ");
            allCodes.append("40414 ; ");
        }
        if (!(allErrors.length() == 0)) {
            throw new ModuleException(allErrors.substring(0, allErrors.length() - 3),
                    allCodes.substring(0, allCodes.length() - 3));
        }
    }
}
