package com.epam.esm.models;

import com.epam.esm.validators.CertificateValidator;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Certificate {
    private int id;
    private String name;
    private String description;
    private Double price;
    private Integer duration;
    private String createDate;
    private String lastUpdateDate;

    public static Certificate mapper(CertificateWithTag certificateWithTag) {
        CertificateValidator validator = new CertificateValidator();
        Certificate certificate = Certificate.builder()
                .name(certificateWithTag.getName())
                .description(certificateWithTag.getDescription())
                .price(certificateWithTag.getPrice())
                .duration(certificateWithTag.getDuration())
                .build();
        //validator.validate(certificate);
        return certificate;
    }
}
