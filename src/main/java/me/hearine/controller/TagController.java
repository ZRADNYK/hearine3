package me.hearine.controller;


import me.hearine.domain.Tag;
import me.hearine.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
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
public class TagController {

    @Autowired
    private TagService tagService;

    @GetMapping("/tag")
    public String tagList(@RequestParam(required = false, defaultValue = "") String tagFilter,
                          Model model) {
            Iterable<Tag> tags;

            if (tagFilter != null && !tagFilter.isEmpty()) {
                tags = Collections.singleton(tagService.findByName(tagFilter));
            } else {
                tags = tagService.findAll();
            }
        model.addAttribute("tags", tags);
        model.addAttribute("tagFilter", tagFilter);
        return "mainTagg";
    }

    @PostMapping("/tag")
    public String add(
            @Valid Tag tag,
            BindingResult bindingResult,
            @RequestParam String name,
            Model model
    ) {
        if (bindingResult.hasErrors()) {
            Map<String, String> errorsMap = ControllerUtils.getErrors(bindingResult);
            model.mergeAttributes(errorsMap);
            model.addAttribute("tag", tag);
        } else {
            model.addAttribute("tag", null);
            tagService.save(tag, name);
        }
        Iterable<Tag> tags = tagService.findAll();
        model.addAttribute("tags", tags);
        return "mainTagg";
    }
}
