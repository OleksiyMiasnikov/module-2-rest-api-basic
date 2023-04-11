package com.epam.esm.models;

import com.epam.esm.util.CertificateValidator;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;

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
        validator.validate(certificate);
        return certificate;
    }
}
