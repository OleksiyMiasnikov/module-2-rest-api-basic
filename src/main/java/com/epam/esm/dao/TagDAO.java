package com.epam.esm.dao;

import com.epam.esm.models.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.List;

@Slf4j
@Repository
@RequiredArgsConstructor
public class TagDAO {

    private final JdbcTemplate jdbcTemplate;

    public List<Tag> index() {
        return jdbcTemplate.query("SELECT * FROM tag", new BeanPropertyRowMapper<>(Tag.class));
    }

    public Tag show(int id){
        return jdbcTemplate.query("SELECT * FROM tag WHERE id=?",
                new Object[]{id},
                new BeanPropertyRowMapper<>(Tag.class))
                .stream()
                .findAny()
                .orElse(null);
    }

    public Tag create(Tag tag) {
        jdbcTemplate.update("INSERT INTO tag VALUES (default, ?)", tag.getName());
        return tag;
    }

    public boolean update(int id,Tag tag) {
        jdbcTemplate.update("UPDATE tag SET name=? WHERE id=?", tag.getName(), id);
        return true;
    }

    public boolean delete(int id) {
        jdbcTemplate.update("DELETE FROM tag WHERE id=?", id);
        return true;
    }
}
