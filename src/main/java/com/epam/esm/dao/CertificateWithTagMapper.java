package com.epam.esm.dao;

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
                .build();
    }
}
