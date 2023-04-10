package com.epam.esm.repositories;

import com.epam.esm.models.Certificate;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.time.ZonedDateTime;
import java.util.List;

@Slf4j
@Repository
@RequiredArgsConstructor
public class CertificateRepository {
//    @Resource
//    @Inject
//    Injects VS Resource(name = "myBean") VS Autowired VS Qualifier
//    How to inject prototype to singleton?
//    Layered
//    @Controller vs @RestController
//
    private final JdbcTemplate jdbcTemplate;

    public List<Certificate> index() {
        return jdbcTemplate.query("SELECT * FROM certificate",
                new BeanPropertyRowMapper<>(Certificate.class));
    }

    public Certificate show(int id){
        return jdbcTemplate.query("SELECT * FROM certificate WHERE id=?",
                        new Object[]{(Object) id},
                        new BeanPropertyRowMapper<>(Certificate.class))
                .stream()
                .findAny()
                .orElse(null);
    }

    public Certificate create(Certificate certificate) {
        String dateTime = ZonedDateTime.now().toLocalDateTime().toString();
        jdbcTemplate.update("INSERT INTO certificate VALUES (default, ?, ?, ?, ?, ?, ?)",
                certificate.getName(),
                certificate.getDescription(),
                (Object) certificate.getPrice(),
                (Object) certificate.getDuration(),
                dateTime,
                dateTime);
        return certificate;
    }

    public boolean update(int id, Certificate certificate) {
        jdbcTemplate.update("UPDATE certificate " +
                        "SET name=?, " +
                            "description=?, " +
                            "price=?, " +
                            "duration=?, " +
                            "last_update_date=? " +
                        "WHERE id=?",
                certificate.getName(),
                certificate.getDescription(),
                (Object) certificate.getPrice(),
                (Object) certificate.getDuration(),
                ZonedDateTime.now().toLocalDateTime().toString(),
                (Object) id);
        return true;
    }

    public void delete(int id) {
        jdbcTemplate.update("DELETE FROM certificate WHERE id=?", (Object) id);
    }
}