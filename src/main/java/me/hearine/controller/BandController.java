package me.hearine.controller;

import me.hearine.domain.Album;
import me.hearine.domain.Band;
import me.hearine.service.BandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.util.Collections;
import java.util.Map;

@Controller
public class BandController {

    @Value("${upload.path}")
    private String uploadPath;

    @Autowired
    private BandService bandService;


    // список всех исполнителей
    @GetMapping("/band")
    public String bandList(@RequestParam(required = false, defaultValue = "") String bandFilter, Model model) {
        Iterable<Band> bands;

        if (bandFilter != null && !bandFilter.isEmpty()) {
            bands = Collections.singleton(bandService.findByName(bandFilter));
        } else {
            bands = bandService.findAll();
        }

        model.addAttribute("bands", bands);
        model.addAttribute("playlistFilter", bandFilter);

        return "mainBand";
    }

    // новый исполнитель
  /*  @PostMapping("/band")
    public String add(
            @Valid Band band,
            BindingResult bindingResult,
            @RequestParam String name,
            @RequestParam String pseudo,
      //      @RequestParam String DOB,
            @RequestParam String gender,
            @RequestParam String bio,
            @RequestParam("file") MultipartFile file,
            Model model
    ) throws IOException {

        if (bindingResult.hasErrors()) {
            Map<String, String> errorsMap = ControllerUtils.getErrors(bindingResult);

            model.mergeAttributes(errorsMap);
            model.addAttribute("band", band);
        } else {
            model.addAttribute("band", null);
            bandService.save(band, name, pseudo, gender, bio, file);
        }
        Iterable<Band> bands = bandService.findAll();
        model.addAttribute("bands", bands);
        return "mainArtist";
    } */

    @PostMapping("/band")
    public String add(
            @Valid Band band,
            BindingResult bindingResult,
            @RequestParam String name,
   //         @RequestParam String pseudo,
            //      @RequestParam String DOB,
   //         @RequestParam String gender,
    //        @RequestParam String bio,
    //        @RequestParam("file") MultipartFile file,
            Model model
    ) {
        if (bindingResult.hasErrors()) {
            Map<String, String> errorsMap = ControllerUtils.getErrors(bindingResult);

            model.mergeAttributes(errorsMap);
            model.addAttribute("band", band);
        } else {
            model.addAttribute("band", null);
      //      bandService.save(band, name, pseudo, gender, bio, file);
            bandService.save(band, name);
        }
        Iterable<Band> bands = bandService.findAll();
        model.addAttribute("bands", bands);
        return "mainBand";
    }

    // альбомы исполнителя по его id
    @GetMapping("/band/{band}/albumList")
    public String bandAlbumList(@PathVariable Band band,
                                   Model model) {

        Iterable<Album> albums = band.getAlbums();

        model.addAttribute("band", band);
        // subs
        model.addAttribute("albums", albums);

        return "parts/band/albumList";
    }
}
