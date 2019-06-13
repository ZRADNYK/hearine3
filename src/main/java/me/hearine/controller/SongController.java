package me.hearine.controller;

import me.hearine.domain.Song;
import me.hearine.domain.User;
import me.hearine.payload.UploadFileResponse;
import me.hearine.service.PlaylistService;
import me.hearine.service.SongService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Controller
public class SongController {


    @Autowired
    private SongService songService;

    @Value("${upload.path}")
    private String uploadPath;

    @GetMapping("/song")
    public String songList(@AuthenticationPrincipal User user,
                                @RequestParam(required = false, defaultValue = "") String songFilter,
                           Model model) {
        Iterable<Song> songs;

        if (songFilter != null && !songFilter.isEmpty()) {
            songs = songService.findByPublicNameContaining(songFilter);
        } else {
            songs = songService.findAll();
        }

        model.addAttribute("isAdmin", user.isAdmin());
        model.addAttribute("songs", songs);
        model.addAttribute("songFilter", songFilter);

        return "mainSong";
    }

  /*  @PostMapping("/song")
    public String add(
            @Valid Song song,
            BindingResult bindingResult,
            @RequestParam String name,
            @RequestParam("file") MultipartFile file,
            Model model
    ) throws IOException {

        if (bindingResult.hasErrors()) {
            Map<String, String> errorsMap = ControllerUtils.getErrors(bindingResult);

            model.mergeAttributes(errorsMap);
            model.addAttribute("song", song);
        } else {
            model.addAttribute("song", null);
            songService.save(song, name, file, uploadPath);
        }

        Iterable<Song> songs = songService.findAll();

        model.addAttribute("songs", songs);

        return "mainSong";
    } */

    private static final Logger logger = LoggerFactory.getLogger(SongController.class);


    @PostMapping("/uploadFile")
    public UploadFileResponse uploadFile(@RequestParam("file") MultipartFile file) throws Exception {
        Song songFromDb = songService.storeFile(file);

        String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("/downloadFile/")
                .path(songFromDb.getId().toString())
                .toUriString();

        return new UploadFileResponse(songFromDb.getPublicName(), fileDownloadUri,
                file.getContentType(), file.getSize());
    }

    @PostMapping("/uploadMultipleFiles")
    public List<UploadFileResponse> uploadMultipleFiles(@RequestParam("files") MultipartFile[] files)  {
        return Arrays.asList(files)
                .stream()
                .map(file -> {
                    try {
                        return uploadFile(file);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    return null;
                })
                .collect(Collectors.toList());
    }


    @GetMapping("/downloadFile/{fileId}")
    public ResponseEntity<Resource> downloadFile(@PathVariable String fileId) throws IOException {
        // Load file from database
        Song song = songService.getFile(Long.valueOf(fileId));
        File file = new File(song.getSongPath());
        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(song.getFileType()))
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + song.getPublicName() + "\"")
                .body(new ByteArrayResource(Files.readAllBytes(Paths.get(song.getSongPath()))));
    }

    @GetMapping("/song/add")
    public String uploadSong(Map<String, Object> model) {
        return "parts/song/uploadSong";
    }







 /*   @GetMapping("/playlist/addsong/{playlist}")
    public String addSongToPlaylist(
            @Valid Song song,
            BindingResult bindingResult,
            @RequestParam Playlist playlist,
            Model model
    ) throws IOException {
        if (bindingResult.hasErrors()) {
            Map<String, String> errorsMap = ControllerUtils.getErrors(bindingResult);
            model.mergeAttributes(errorsMap);
            model.addAttribute("song", song);
        } else {
            song.addToPlaylist(playlist);
            model.addAttribute("song", song);
            songService.update(song, playlist);
        }
        return "songList";
    }*/
}

