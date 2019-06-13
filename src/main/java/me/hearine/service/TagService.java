package me.hearine.service;

import me.hearine.domain.Tag;
import me.hearine.repos.TagRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TagService {

    @Autowired
    private TagRepo tagRepo;

    public Tag findByName(String name) {
        return tagRepo.findByName(name);
    }

    public List<Tag> findAll() {
        return tagRepo.findAll();
    }

    public void save(Tag tag, String name) {
        tag.setName(name);
        tagRepo.save(tag);
    }

    public void update(Tag tag, String name) {
        tag.setName(name);
        tagRepo.save(tag);
    }


}
