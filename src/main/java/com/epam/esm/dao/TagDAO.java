package com.epam.esm.dao;

import com.epam.esm.models.Tag;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

@Component
public class TagDAO {
    private static int TAG_COUNT;
    private List<Tag> tagList;
    {
        tagList = new LinkedList<Tag>();
        tagList.add(Tag.builder()
                .id(++TAG_COUNT)
                .name("Tag_01")
                .build());
        tagList.add(Tag.builder()
                .id(++TAG_COUNT)
                .name("Tag_02")
                .build());
        tagList.add(Tag.builder()
                .id(++TAG_COUNT)
                .name("Tag_03")
                .build());
        tagList.add(Tag.builder()
                .id(++TAG_COUNT)
                .name("Tag_04")
                .build());
    }

    public List<Tag> index() {
        return tagList;
    }

    public Tag show(int id){
        return tagList.stream().filter(t -> t.getId() == id).findAny().orElse(null);
    }

    public Tag create(Tag tag) {
        tag.setId(++TAG_COUNT);
        tagList.add(tag);
        return tag;
    }

    public boolean update(int id,Tag tag) {
        for (Tag element : tagList) {
            if (element.getId() == id) {
                element.setName(tag.getName());
                return true;
            }
        }
        return false;
    }

    public void delete(int id) {
        tagList.removeIf((t) -> t.getId() == id);
    }
}
