package com.epam.esm.repositories;

import com.epam.esm.models.Tag;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;
import org.springframework.context.annotation.Bean;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.sql.DataSource;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mockStatic;

@Slf4j
class TagRepositoryTest {
    private final JdbcTemplate jdbcTemplate  = new JdbcTemplate(dataSource());
    private final TagRepository repo = new TagRepository(jdbcTemplate);
    private final String CREATE_TABLE = "CREATE TABLE tag (" +
            "  id INT AUTO_INCREMENT," +
            "  name VARCHAR(45)," +
            "  PRIMARY KEY (id))";
    private final String DROP_TABLE = "DROP TABLE tag";

    private DataSource dataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");
        dataSource.setUrl("jdbc:h2:mem:test;DB_CLOSE_DELAY=-1");
        dataSource.setUsername("root");
        dataSource.setPassword("18De1975");
        return dataSource;
    }

    @BeforeEach
    void init() {
        jdbcTemplate.update(CREATE_TABLE);
        repo.create("tag_1");
        repo.create("tag_2");
        repo.create("tag_3");
    }
    @AfterEach
    void tearEach(){
        jdbcTemplate.update(DROP_TABLE);
    }

    @Test
    void create() {
        String name = "test-tag";
        Tag tag = Tag.builder()
                .id(1)
                .name(name)
                .build();
        int createdId = repo.create(name);
        assertThat(createdId).isNotNegative();
    }

    @Test
    void findAll() {
        List<Tag> expectedList = new LinkedList<>();
        expectedList.add(Tag.builder()
                .id(1)
                .name("tag_1")
                .build());
        expectedList.add(Tag.builder()
                .id(2)
                .name("tag_2")
                .build());
        expectedList.add(Tag.builder()
                .id(3)
                .name("tag_3")
                .build());
        assertThat(repo.findAll()).isEqualTo(expectedList);
    }

    @Test
    void findById() {
        Tag expectedTag = Tag.builder()
                .id(2)
                .name("tag_2")
                .build();
        Optional<Tag> actual = repo.findById(2);
        assertThat(actual).isPresent();
        assertThat(actual.get()).isEqualTo(expectedTag);
    }

    @Test
    void findByName() {
        List <Tag> expected = new LinkedList<>(List.of(Tag.builder()
                                                        .id(2)
                                                        .name("tag_2")
                                                        .build()));
        assertThat(repo.findByName("tag_2")).isEqualTo(expected);
    }

    @Test
    void delete() {
        assertThat(repo.delete(1)).isTrue();
        assertThat(repo.delete(1)).isFalse();
    }
}