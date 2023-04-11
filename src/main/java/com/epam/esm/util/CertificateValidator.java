package com.epam.esm.util;

import com.epam.esm.models.Certificate;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class CertificateValidator implements Validator {
    @Override
    public boolean supports(Class<?> clazz) {
        return Certificate.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        Certificate certificate = (Certificate) target;
        if ((certificate.getName() == null) || (certificate.getName().isBlank())) {
            errors.reject("40411", "Field 'name' can not be empty!");
        }
        if ((certificate.getDescription() == null) || (certificate.getDescription().isBlank())) {
            errors.reject("40412","Field 'description' can not be empty!");
        }
        if ((certificate.getPrice() == null) || (certificate.getPrice() <= 0)) {
            errors.reject("40413","Field 'price' should be more then 0!");
        }
        if ((certificate.getDuration() == null) || (certificate.getDuration() <= 0)) {
            errors.reject("40414","Field 'duration' should be more then 0!");
        }

    }
}
