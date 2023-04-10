package com.epam.esm.repositories;

import com.epam.esm.models.CertificateWithTag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

@Slf4j
public class CertificateWithTagMapper implements RowMapper<CertificateWithTag> {
    @Override
    public CertificateWithTag mapRow(ResultSet rs, int rowNum) throws SQLException {
        //log.info("Mapper loaded");
        return CertificateWithTag.builder()
                .tagId(rs.getInt("tag_id"))
                .certificateId(rs.getInt("certificate_id"))
                .certificateName(rs.getString("certificate_name"))
                .description(rs.getString("description"))
                .price(rs.getDouble("price"))
                .duration(rs.getInt("duration"))
                .createDate(rs.getString("create_date"))
                .lastUpdateDate(rs.getString("last_update_date"))
                .tagName(rs.getString("tag_name"))
                .build();
    }
}