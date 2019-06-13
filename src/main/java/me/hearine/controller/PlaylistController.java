package me.hearine.controller;


import me.hearine.domain.Album;
import me.hearine.domain.Playlist;
import me.hearine.domain.Song;
import me.hearine.domain.User;
import me.hearine.service.AlbumService;
import me.hearine.service.PlaylistService;
import me.hearine.service.SongService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.io.IOException;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Set;

@Controller
public class PlaylistController {

    @Autowired
    private PlaylistService playlistService;

    @Autowired
    private SongService songService;

    @Autowired
    private AlbumService albumService;

    // список  публичных плейлистов
    @GetMapping("/playlist")
    public String playlistList(@RequestParam(required = false, defaultValue = "") String playlistFilter,
                               @AuthenticationPrincipal User user, Model model) {  // fixme

        List<Playlist> playlists = playlistService.searchBy(playlistFilter, "public");

        model.addAttribute("playlists", playlists);
        model.addAttribute("user", user);
        model.addAttribute("isAdmin", user.isAdmin());
        model.addAttribute("playlistFilter", playlistFilter);

        return "mainPlaylist";
    }


    // новый плейлист
    @PostMapping("/playlist")
    public String add(
            @AuthenticationPrincipal User user,
            @Valid Playlist playlist,
            BindingResult bindingResult,
            @RequestParam String name,
            @RequestParam String lstType,
            @RequestParam String lstAccess,
            @RequestParam("file") MultipartFile file,
            Model model
    ) throws Exception {
        if (bindingResult.hasErrors()) {
            Map<String, String> errorsMap = ControllerUtils.getErrors(bindingResult);

            model.mergeAttributes(errorsMap);
            model.addAttribute("playlist", playlist);
        } else {


            model.addAttribute("playlist", null);

            playlistService.save(user, playlist, name, lstType, lstAccess, file);
        }

        Iterable<Playlist> playlists = playlistService.findAll();

        model.addAttribute("playlists", playlists);

        return "mainPlaylist";
    }

    // песни из плейлиста по его id
    @GetMapping("/playlist/{playlist}/songList")
    public String playlistSongList(@PathVariable Playlist playlist,
                                   Model model) {

        Iterable<Song> songs = playlist.getSongs();

        model.addAttribute("playlist", playlist);
        // subs
        model.addAttribute("songs", songs);

        return "parts/playlist/playlistSongList";
    }


    // песни, которых НЕТ в плейлисте
    @GetMapping("/playlist/{playlist}/availableSongs")
    public String playlistAvailableSongs(
            @RequestParam(required = false, defaultValue = "") String availableSongsFilter,
            @AuthenticationPrincipal User user,
            @PathVariable Playlist playlist,
            Model model) {
        Iterable<Song> songs = songService.findSongsByAddedToPlaylistsIsNotContaining(playlist.getId());
        if (availableSongsFilter != null && !availableSongsFilter.isEmpty()) {
            songs = songService.findByPublicName(availableSongsFilter);
        }
        model.addAttribute("songs", songs);
        model.addAttribute("availableSongsFilter", availableSongsFilter);
        model.addAttribute("playlist", playlist);
        return "parts/playlist/playlistAvailableSongs";
    }


    // положить в плейлист новые песни
    @PostMapping("/playlist/{playlist}/availableSongs")
    public String addSongsToPlaylist(
            @AuthenticationPrincipal User user,
            @PathVariable Playlist playlist,
            @RequestParam Map<String, String> form
    ) throws IOException {
        Set<Song> songs = songService.findSongsByAddedToPlaylistsIsNotContaining(playlist.getId());
        playlistService.addSongs(playlist, form);

        return "redirect:/playlist/" + playlist.getId().toString() + "/songList";
    }

    // вывести ВСЕ альбомы
    @GetMapping("/playlist/{playlist}/availableAlbums")
    public String playlistAvailableAlbums( @RequestParam(required = false, defaultValue = "") String availableAlbumsFilter,
                                           @AuthenticationPrincipal User user,
                                           @PathVariable Playlist playlist,
                                           Model model) {
        Iterable<Album> albums = albumService.findAll();
        if (availableAlbumsFilter != null && !availableAlbumsFilter.isEmpty()) {
            albums = albumService.findByNameContaining(availableAlbumsFilter);
        }
        model.addAttribute("albums", albums);
        model.addAttribute("availableAlbumsFilter", availableAlbumsFilter);
        model.addAttribute("playlist", playlist);
        return "parts/playlist/playlistAvailableAlbums";
    }

    // положить в плейлист новые песни по альбому
    @PostMapping("/playlist/{playlist}/availableAlbums")
    public String addAlbumToPlaylist(
            @AuthenticationPrincipal User user,
            @PathVariable Playlist playlist,
            @RequestParam Map<String, String> form
    ) throws IOException {
        List<Album> albums = albumService.findAll();
        playlistService.addSongsFromAlbums(playlist, form);

        return "redirect:/playlist/" + playlist.getId().toString() + "/songList";
    }


      /*  String[] ids = song.split(songSeparator);
        Set<Song> songs = new HashSet<>();
        for (String id : ids) {
            Song nextSong = songService.findByPrivateName(id);
            songs.add(nextSong);
        }
        Playlist p = playlistService.findByName(playlist);
        p.addSongs(songs);
        playlistService.save(user, p, playlist);
        model.addAttribute("p", p);
        model.addAttribute("songs", songs);*/

}
/*
    private void saveFile(@Valid Playlist playlist, @RequestParam("file") MultipartFile file) throws IOException {
        if (file != null && !file.getOriginalFilename().isEmpty()) {
            File uploadDir = new File(uploadPath);

            if (!uploadDir.exists()) {
                uploadDir.mkdir();
            }

            String uuidFile = UUID.randomUUID().toString();
            String resultFilename = uuidFile + "." + file.getOriginalFilename();

            file.transferTo(new File(uploadPath+ "/" + resultFilename));

            playlist.setAvatar(resultFilename);
        }
    }

}*


  /*  //  @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("{playlist}")
    public String playlistEditForm(@PathVariable Playlist playlist, Model model) {
        model.addAttribute("playlist", playlist);
//        model.addAttribute("roles", Role.values());

        return "playlistEdit";
    }

     @PreAuthorize("hasAuthority('ADMIN')")
    @PostMapping
    public String userSave(
            @RequestParam String username,
            @RequestParam Map<String, String> form,
            @RequestParam("userId") User user
    ) {
        playlistService.savePlaylist();
        userService.saveUser(user, username, form);

        return "redirect:/user";
    }*/


/*
    @Autowired
    private PlaylistService playlistService;

    @GetMapping
    public String playlistList(Model model) {
        model.addAttribute("users", playlistService.findAll());

        return "userList";
    }

    // @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("{playlist}")
    public String playlistEditForm(//@RequestParam User user,
                                   @RequestParam Playlist playlist,
                                   // some other stuff to edit
                                   // like songs etc
                                   Model model) {
        //   model.addAttribute("user", user);
        model.addAttribute("playlist", playlist);

        return "playlistEdit";
    }

    //  @PreAuthorize("hasAuthority('ADMIN')")
    @PostMapping
    public String playlistSave(
            @RequestParam String playlistName,
            @RequestParam("authorId") User author,
            @RequestParam("playlistId") Playlist playlist
    ) {
        playlistService.savePlaylist(author, playlist, playlistName);

        return "redirect:/playlist";
    }
*/