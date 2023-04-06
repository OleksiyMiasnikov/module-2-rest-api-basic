package com.epam.esm.dao;

import com.epam.esm.models.Certificate;
import com.epam.esm.models.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.LinkedList;
import java.util.List;

@Component
public class CertificateDAO {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    public List<Certificate> index() {
        return jdbcTemplate.query("SELECT * FROM certificates",
                new BeanPropertyRowMapper<>(Certificate.class));
    }

    public Certificate show(int id){
        return jdbcTemplate.query("SELECT * FROM certificates WHERE id=?",
                        new Object[]{id},
                        new BeanPropertyRowMapper<>(Certificate.class))
                .stream()
                .findAny()
                .orElse(null);
    }

    public Certificate create(Certificate certificate) {
        jdbcTemplate.update("INSERT INTO certificates VALUES (default, ?, ?, ?, ?, ?, ?)",
                certificate.getName(),
                certificate.getDescription(),
                certificate.getPrice(),
                certificate.getDuration(),
                certificate.getCreateDate(),
                certificate.getLastUpdateDate());
        return certificate;
    }

    public boolean update(int id, Certificate certificate) {
        jdbcTemplate.update("UPDATE certificates " +
                        "SET name=? " +
                            "description=? " +
                            "price=? " +
                            "duration=? " +
                            "create_date=? " +
                            "last_update_date=? " +
                        "WHERE id=?",
                certificate.getName(),
                certificate.getDescription(),
                certificate.getPrice(),
                certificate.getDuration(),
                certificate.getCreateDate(),
                certificate.getLastUpdateDate(),
                id);
        return true;
    }

    public void delete(int id) {
        jdbcTemplate.update("DELETE FROM certificates WHERE id=?", id);
    }
}
