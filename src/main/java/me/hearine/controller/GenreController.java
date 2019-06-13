package me.hearine.controller;

import me.hearine.domain.Genre;
import me.hearine.domain.User;
import me.hearine.service.GenreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;
import java.io.IOException;
import java.util.Collections;
import java.util.Map;

@Controller
public class GenreController {

    @Autowired
    private GenreService genreService;

    @GetMapping("/genre")
    public String genreList(@RequestParam(required = false, defaultValue = "") String genreFilter, Model model) {
        Iterable<Genre> genres;

        if (genreFilter!= null && !genreFilter.isEmpty()) {
            genres = Collections.singleton(genreService.findByName(genreFilter));
        } else {
            genres = genreService.findAll();
        }
        model.addAttribute("genres", genres);
        model.addAttribute("genreFilter", genreFilter);
        return "mainGenre";
    }

    @PostMapping("/genre")
    public String add(
            @Valid Genre genre,
            BindingResult bindingResult,
            @RequestParam String name,
            Model model
    ) {
        if (bindingResult.hasErrors()) {
            Map<String, String> errorsMap = ControllerUtils.getErrors(bindingResult);
            model.mergeAttributes(errorsMap);
            model.addAttribute("genre", genre);
        } else {
            model.addAttribute("genre", null);
            genreService.save(genre, name);
        }
        Iterable<Genre> genres = genreService.findAll();
        model.addAttribute("genres", genres);
        return "mainGenre";
    }

}
