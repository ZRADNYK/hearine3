package me.hearine.service;

import me.hearine.domain.Album;
import me.hearine.domain.Playlist;
import me.hearine.domain.Song;
import me.hearine.domain.User;
import me.hearine.domain.cloud.CloudinaryUtils;
import me.hearine.exception.FileStorageException;
import me.hearine.repos.AlbumRepo;
import me.hearine.repos.PlaylistRepo;
import me.hearine.repos.SongRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.*;
import java.util.logging.Logger;

@Service
public class PlaylistService {

    @Autowired
    private PlaylistRepo playlistRepo;

    @Autowired
    private SongRepo songRepo;

    @Autowired
    private AlbumRepo albumRepo;

    private static Logger log = Logger.getLogger(PlaylistService.class.getName());


    public boolean addPlaylist(Playlist playlist) {
        Playlist playlistFromDb = playlistRepo.findByName(playlist.getName());

        if (playlistFromDb != null) {
            return false;
        }

        // other stuff if needed

        playlistRepo.save(playlist);

        return true;
    }

    public boolean compare(String authorName, String username) {
        return authorName.equals(username);
    }

    public void save(User author, Playlist playlist, String playlistName,
                     String list_type, String list_access, MultipartFile file
    ) throws Exception {

        playlist.setAuthor(author);
        playlist.setCreate_date(new java.sql.Date(System.currentTimeMillis()));
        playlist.setName(playlistName);
        playlist.setLstType(list_type);
        playlist.setLstAccess(list_access);
        if (file != null) {
            String avatarPath = storeFile(file);
            playlist.setAvatar(avatarPath);
        }
        playlistRepo.save(playlist);
        log.info("Playlist " + playlistName + " has been uploaded to Cloudinary successfully");

    }

    private String storeFile(MultipartFile file) throws Exception {
        String uuidFile = UUID.randomUUID().toString();
        String resultFilename = uuidFile + "." + file.getName();

        log.info("playlist avatar " + file.getName() + " now has name " + resultFilename);
        log.info("Starting uploading image " + resultFilename + " to Cloudinary storage");

        String url = CloudinaryUtils.uploadFileToCloud(file, resultFilename);

        // Check if the file's name contains invalid characters
        if (resultFilename.contains("..")) {
            log.warning("playlist name " + resultFilename + " has invalid characters");
            throw new FileStorageException("Sorry! Filename contains invalid path sequence " + resultFilename);
        }
        log.info("Image " + resultFilename + " has been uploaded to Cloudinary successfully");

        return url;
    }


    public void addSongs(Playlist playlist, Map<String, String> form) {

        playlist.setCreate_date(new java.sql.Date(System.currentTimeMillis()));
        for (String key : form.keySet()) {
            Song nextSong = songRepo.findByPrivateName(key);
            if (nextSong != null) {
                playlist.addSongs(Collections.singleton(nextSong));
            }
        }

        playlistRepo.save(playlist);
    }

    public void addSongsFromAlbums(Playlist playlist, Map<String, String> form) {

        playlist.setCreate_date(new java.sql.Date(System.currentTimeMillis()));
        for (String key : form.keySet()) {
            Album nextAlbum = albumRepo.findByName(key);
            if (nextAlbum != null) {
                for (Song nextSong : nextAlbum.getSongs()) // fix
                    if (nextSong != null) {
                        playlist.addSongs(Collections.singleton(nextSong));
                    }
            }
            //      else break;
        }
        playlistRepo.save(playlist);
    }


    public List<Playlist> findAll() {
        return playlistRepo.findAll();
    }

    public Playlist findByName(String name) {
        return playlistRepo.findByName(name);
    }

    public List<Playlist> findByNameContaining(String name) {
        return playlistRepo.findByNameContaining(name);
    }

    public List<Playlist> findByLstTypeContaining(String type) {
        return playlistRepo.findByLstTypeContaining(type);
    }

    public List<Playlist> findByDscContaining(String dsc) {
        return playlistRepo.findByDscContaining(dsc);
    }

    public List<Playlist> findByLstAccess(String access) {
        return playlistRepo.findByLstAccess(access);
    }

    public List<Playlist> findByAuthorAndLstAccess(User author, String access) {
        return playlistRepo.findByAuthorAndLstAccess(author, access);
    }

    public List<Playlist> findByNameContainingAndLstAccess(String name, String lstAccess) {
        return playlistRepo.findByNameContainingAndLstAccess(name, lstAccess);
    }


    public List<Playlist> searchBy(String playlistFilter, String access) {
        List<Playlist> playlists;
        if (playlistFilter != null && !playlistFilter.isEmpty()) {
            playlists = playlistRepo.findByNameContaining(playlistFilter);
            if (playlists.size() == 0) {
                playlists = playlistRepo.findByLstTypeContaining(playlistFilter);
            }
            if (playlists.size() == 0) {
                playlists = playlistRepo.findByDscContaining(playlistFilter);
            }
        } else {
            playlists = playlistRepo.findByLstAccess(access);
        }
        return playlists;
    }
}
