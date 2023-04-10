package com.epam.esm.repositories;

import com.epam.esm.models.CertificateWithTag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Slf4j
@Repository
@RequiredArgsConstructor
public class CertificateWithTagRepository {

    private final JdbcTemplate jdbcTemplate;
    private final String JOIN_SQL =
            "SELECT " +
                "name as certificate_name, " +
                "description, " +
                "price, " +
                "duration, " +
                "create_date, " +
                "last_update_date, " +
                "certificate_id, " +
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


    public void create(int tagId, int certificateId){
        log.info("Repository. Create certificate with tag");
        jdbcTemplate.update("INSERT INTO certificate_with_tag (tag_id, certificate_id) VALUES (?, ?)",
                tagId, certificateId);
    }

    public List<CertificateWithTag> findAll() {
        log.info("Repository. Find all certificates with tags");
        return jdbcTemplate.query(JOIN_SQL,
                new CertificateWithTagMapper());
    }

    public List<CertificateWithTag> findByTagName(String name) {
        log.info("Repository. Find all certificates with tag: " + name);
        return jdbcTemplate.query("SELECT * FROM (" + JOIN_SQL + ") all_tb WHERE all_tb.tag_name=?",
                        new Object[]{name},
                        new CertificateWithTagMapper());
    }

    public Optional<CertificateWithTag> findByTagIdAndCertificateId(Integer tagId, Integer certificateId) {
        log.info("Repository. Find certificate by tagId and certificateId");
        return jdbcTemplate.query("SELECT * FROM (" +
                        JOIN_SQL +
                        ") all_tb WHERE all_tb.tag_id=? AND all_tb.certificate_id=?",
                new Object[]{tagId, certificateId},
                new CertificateWithTagMapper())
                .stream()
                .findAny();
    }
}
