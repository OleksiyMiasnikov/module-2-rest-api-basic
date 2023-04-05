package com.epam.esm.dao;

import com.epam.esm.models.Certificate;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

@Component
public class CertificateDAO {
    private static int CERTIFICATE_COUNT;
    private List<Certificate> certificateList;
    {
        certificateList = new LinkedList<Certificate>();
        certificateList.add(Certificate.builder()
                .id(++CERTIFICATE_COUNT)
                .name("Certificate_01")
                .description("week")
                .price(100)
                .duration(7)
                .createDate(new SimpleDateFormat())
                .lastUpdateDate(new SimpleDateFormat())
                .build());
        certificateList.add(Certificate.builder()
                .id(++CERTIFICATE_COUNT)
                .name("Certificate_02")
                .description("month")
                .price(1000)
                .duration(30)
                .createDate(new SimpleDateFormat())
                .lastUpdateDate(new SimpleDateFormat())
                .build());
        certificateList.add(Certificate.builder()
                .id(++CERTIFICATE_COUNT)
                .name("Certificate_03")
                .description("half year")
                .price(100.50)
                .duration(180)
                .createDate(new SimpleDateFormat())
                .lastUpdateDate(new SimpleDateFormat())
                .build());
        certificateList.add(Certificate.builder()
                .id(++CERTIFICATE_COUNT)
                .name("Certificate_04")
                .description("year")
                .price(20.48)
                .duration(365)
                .createDate(new SimpleDateFormat())
                .lastUpdateDate(new SimpleDateFormat())
                .build());
    }

    public List<Certificate> index() {
        return certificateList;
    }

    public Certificate show(int id){
        return certificateList.stream().filter(s -> s.getId() == id).findAny().orElse(null);
    }

    public Certificate create(Certificate Certificate) {
        Certificate.setId(++CERTIFICATE_COUNT);
        certificateList.add(Certificate);
        return Certificate;
    }

    public boolean update(int id,Certificate Certificate) {
        for (Certificate element : certificateList) {
            if (element.getId() == id) {
                element.setName(Certificate.getName());
//                element.setDescription(Certificate.getDescription());
//                element.setPrice(Certificate.getPrice());
//                element.setDuration(Certificate.getDuration());
//                element.setCreateDate(Certificate.getCreateDate());
//                element.setLastUpdateDate(Certificate.getLastUpdateDate());
                return true;
            }
        }
        return false;
    }

    public void delete(int id) {
        certificateList.removeIf((s) -> s.getId() == id);
    }
}
