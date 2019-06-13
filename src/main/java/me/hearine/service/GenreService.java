package me.hearine.service;

import me.hearine.domain.Genre;
import me.hearine.repos.GenreRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
public class GenreService {

    @Autowired
    private GenreRepo genreRepo;

    public Genre findByName(String name) {
        return genreRepo.findByName(name);
    }


    public List<Genre> findAll() {
        return genreRepo.findAll();
    }


    public void save(Genre genre, String name) {
        genre.setName(name);
        genreRepo.save(genre);
    }

    public void update(Genre genre, String name) {
        genre.setName(name);
        genreRepo.save(genre);
    }
}
