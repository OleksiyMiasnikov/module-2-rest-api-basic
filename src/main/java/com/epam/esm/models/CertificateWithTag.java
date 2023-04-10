package com.epam.esm.models;

import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.Min;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
/*
certificate_name
description
price
duration
create_date
last_update_date
tag_id
tag_name
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CertificateWithTag {
    private String tag;
    private String name;
    private String description;
    private double price;
    private int duration;
    private String createDate;
    private String lastUpdateDate;
}
