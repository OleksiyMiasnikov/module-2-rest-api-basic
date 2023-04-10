package com.epam.esm.dao;

import com.epam.esm.models.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;


import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.List;

@Slf4j
@Repository
@RequiredArgsConstructor
public class TagDAO {

    private final JdbcTemplate jdbcTemplate;

    public Tag create(String name) {
        log.info("DAO. Create tag with name: " + name);
        final String SQL = "INSERT INTO tag VALUES (default, ?)";
        KeyHolder keyHolder = new GeneratedKeyHolder();

        jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection
                    .prepareStatement(SQL, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, name);
            return ps;
        }, keyHolder);

        return findById(keyHolder.getKey().intValue());
    }

    public List<Tag> findAll() {
        log.info("DAO. Find all tags");
        return jdbcTemplate.query("SELECT * FROM tag", new BeanPropertyRowMapper<>(Tag.class));
    }

    public Tag findById(int id){
        log.info("DAO. Find tag by id: " + id);
        return jdbcTemplate.query("SELECT * FROM tag WHERE id=?",
                new Object[]{(Object) id},
                new BeanPropertyRowMapper<>(Tag.class))
                .stream()
                .findAny()
                .orElse(null);
    }

    public boolean delete(int id) {
        log.info("DAO. Delete tag by id: " + id);
        int result = jdbcTemplate.update("DELETE FROM tag WHERE id=?", (Object) id);
        log.info("result of deleting " + result);
        return result == 1;
    }
}
