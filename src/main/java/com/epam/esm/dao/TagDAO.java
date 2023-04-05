package com.epam.esm.dao;

import com.epam.esm.models.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.LinkedList;
import java.util.List;

@Component
public class TagDAO {
    private static int TAG_COUNT;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private final List<Tag> tagList;
    {
        tagList = new LinkedList<>();
        tagList.add(new Tag(++TAG_COUNT, "Tag_01"));
        tagList.add(new Tag(++TAG_COUNT, "Tag_02"));
        tagList.add(new Tag(++TAG_COUNT, "Tag_03"));
        tagList.add(new Tag(++TAG_COUNT, "Tag_04"));

//        tagList.add(Tag.builder()
//                .id(++TAG_COUNT)
//                .name("Tag_01")
//                .build());
//        tagList.add(Tag.builder()
//                .id(++TAG_COUNT)
//                .name("Tag_02")
//                .build());
//        tagList.add(Tag.builder()
//                .id(++TAG_COUNT)
//                .name("Tag_03")
//                .build());
//        tagList.add(Tag.builder()
//                .id(++TAG_COUNT)
//                .name("Tag_04")
//                .build());
    }

    public List<Tag> index() {
        return jdbcTemplate.query("SELECT * FROM tags", new BeanPropertyRowMapper<>(Tag.class));
    }

    public Tag show(int id){
        return jdbcTemplate.query("SELECT * FROM tags WHERE id=?",
                new Object[]{id},
                new BeanPropertyRowMapper<>(Tag.class))
                .stream()
                .findAny()
                .orElse(null);
    }

    public Tag create(Tag tag) {
        jdbcTemplate.update("INSERT INTO tags VALUES (1, ?)", tag.getName());
        return tag;
    }

    public boolean update(int id,Tag tag) {
        jdbcTemplate.update("UPDATE Tag SET name=? WHERE id=?", tag.getName(), id);
        return true;
    }

    public void delete(int id) {
        jdbcTemplate.update("DELETE * FROM tags WHERE id=?", id);
    }
}
