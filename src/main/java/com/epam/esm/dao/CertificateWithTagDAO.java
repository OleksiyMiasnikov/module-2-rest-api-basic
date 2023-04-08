package com.epam.esm.dao;

import com.epam.esm.models.CertificateWithTag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Slf4j
@Repository
@RequiredArgsConstructor
public class CertificateWithTagDAO {

    private final JdbcTemplate jdbcTemplate;
    private final String JOIN_SQL =
            "SELECT certificate_id, " +
                "name as certificate_name, " +
                "description, " +
                "price, " +
                "duration, " +
                "create_date, " +
                "last_update_date, " +
                "tag_id, " +
                "tag_name " +
            "FROM  certificate " +
            "JOIN (  " +
                    "SELECT certificate_id, " +
                            "name as tag_name, " +
                            "tag_id " +
                    "FROM certificate_with_tag " +
                    "JOIN tag " +
                    "WHERE certificate_with_tag.tag_id = tag.id" +
                 ") tag_tb " +
            "WHERE tag_tb.certificate_id = certificate.id";


    public List<CertificateWithTag> index() {
        log.info("index loaded");
        return jdbcTemplate.query(JOIN_SQL,
                new CertificateWithTagMapper());
    }

    public List<CertificateWithTag> showByTagName(String name) {
        log.info("showByTagId loaded");
        return jdbcTemplate.query("SELECT * FROM (" + JOIN_SQL + ") all_tb WHERE all_tb.tag_name=?",
                        new Object[]{name},
                        new BeanPropertyRowMapper<>(CertificateWithTag.class));
    }
}
