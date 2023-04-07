package com.epam.esm.dao;

import com.epam.esm.models.Certificate;
import com.epam.esm.models.CertificateWithTag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.time.ZonedDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Component
public class CertificateWithTagDAO {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public List<CertificateWithTag> index() {
        log.info("index loaded");
        return jdbcTemplate.query("SELECT * FROM certificate_with_tag",
                new CertificateWithTagMapper());
    }

    public List<CertificateWithTag> showByTagId(int id) {
        log.info("showByTagId loaded");
        return jdbcTemplate.query("SELECT * FROM certificate_with_tag WHERE tag_id=?",
                        new Object[]{id},
                        new BeanPropertyRowMapper<>(CertificateWithTag.class));
    }
}
