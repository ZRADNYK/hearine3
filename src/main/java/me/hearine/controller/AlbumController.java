package me.hearine.controller;


import me.hearine.domain.Album;
import me.hearine.domain.Song;
import me.hearine.service.AlbumService;
import me.hearine.service.SongService;
import org.springframework.beans.factory.annotation.Autowired;
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
public class AlbumController {



    @Autowired
    private AlbumService albumService;

    @Autowired
    private SongService songService;

    // список всех альбомов
    @GetMapping("/album")
    public String albumList(@RequestParam(required = false, defaultValue = "") String albumFilter, Model model) {
        Iterable<Album> albums;

        if (albumFilter != null && !albumFilter.isEmpty()) {
            albums = Collections.singleton(albumService.findByName(albumFilter));
        } else {
            albums= albumService.findAll();
        }

        model.addAttribute("albums", albums);
        model.addAttribute("albumFilter", albumFilter);

        return "mainAlbum";
    }

    // новый альбом
    @PostMapping("/album")
    public String add(
            @Valid Album album,
            BindingResult bindingResult,
            @RequestParam String name,
       //     @RequestParam String release_date,
      //      @RequestParam String dsc,
            @RequestParam String bands,
            @RequestParam String tags,
            @RequestParam String genres,
            @RequestParam("file") MultipartFile file,
            Model model
    ) {

   /*     if (bindingResult.hasErrors()) {
            Map<String, String> errorsMap = ControllerUtils.getErrors(bindingResult);

            model.mergeAttributes(errorsMap);
            model.addAttribute("album", album);
        } else {*/

            model.addAttribute("album", null);

            albumService.save(album, name, bands, tags, genres, file);
       // }

        Iterable<Album> albums = albumService.findAll();

        model.addAttribute("albums", albums);

        return "mainAlbum";
    }


    // песни из альбома по его id
    @GetMapping("/album/{album}/songList")
    public String albumSongList(@PathVariable Album album,
                                   Model model) {

        Iterable<Song> songs = album.getSongs();

        model.addAttribute("album", album);
        // subs
        model.addAttribute("songs", songs);

        return "parts/album/albumSongList";
    }

    // песни, которых НЕТ в альбоме (поиск по всем песням)
    @GetMapping("/album/{album}/availableSongs")
    public String albumAvailableSongs(
            @RequestParam(required = false, defaultValue = "") String availableSongsFilter,
            @PathVariable Album album,
            Model model) {
        Iterable<Song> songs;

        if (availableSongsFilter != null && !availableSongsFilter.isEmpty()) {
            songs = songService.findByPublicName(availableSongsFilter);
        }
        else {
            songs = songService.findSongsByAddedToAlbumsIsNotContaining(album.getId());
        }
        model.addAttribute("songs", songs);
        model.addAttribute("availableSongsFilter", availableSongsFilter);
        model.addAttribute("album", album);
        return "parts/album/albumAvailableSongs";
    }


    // положить в альбом новые песни
    @PostMapping("/album/{album}/availableSongs")
    public String addSongsToAlbum(
            @PathVariable Album album,
            @RequestParam Map<String, String> form
    ) {
        albumService.addSongs(album, form);

        return "redirect:/album/" + album.getId() + "/songList";
    }
}
