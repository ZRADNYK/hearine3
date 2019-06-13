package me.hearine.service;

import me.hearine.controller.ControllerUtils;
import me.hearine.domain.*;
import me.hearine.domain.cloud.CloudinaryUtils;
import me.hearine.exception.FileStorageException;
import me.hearine.repos.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.*;
import java.util.logging.Logger;

@Service
public class AlbumService {

    private static Logger log = Logger.getLogger(AlbumService.class.getName());

    @Autowired
    private AlbumRepo albumRepo;

    @Autowired
    private TagRepo tagRepo;

    @Autowired
    private GenreRepo genreRepo;

    @Autowired
    private SongRepo songRepo;

    @Autowired
    private BandRepo bandRepo;

    public Album findByName(String name) {
        return albumRepo.findByName(name);
    }

    public Set<Album> findByTags(String tags) {
        return albumRepo.findByTags(tags);
    }

    public Set<Album> findByGenres(String genres) {
        return albumRepo.findByGenres(genres);
    }

    public Set<Album> findByBands(String bands) {
        return albumRepo.findByBands(bands);
    }

    public Set<Album> findByNameContaining(String name) {
        return albumRepo.findByNameContaining(name);
    }

    public List<Album> findAll() {
        return albumRepo.findAll();
    }

    public boolean addAlbum(Album album) {
        Album albumFromDb = albumRepo.findByName(album.getName());

        if (albumFromDb != null) {
            return false;
        }

        // other stuff if needed
        albumRepo.save(album);
        return true;
    }


    public void save(Album album, String name,// String release_date,
                     String bands, String tags,
                     String genres, MultipartFile file) {
        album.setName(name);
       /* try {
            SimpleDateFormat sdf1 = new SimpleDateFormat("MM-dd-yyyy");
            java.util.Date date = sdf1.parse(release_date);
            java.sql.Date sqlStartDate = new java.sql.Date(date.getTime());
            album.setRelease_date(sqlStartDate);
        }
        catch (ParseException ex) {
            ex.printStackTrace();
            album.setRelease_date(null);
        }*/

        Band band = null;
        if(bands.contains(", ")) {
            String[] names = bands.split(", ");
            for (String bandName : names) {
                band = bandRepo.findByName(bandName);
                if (band == null) {
                    band = new Band();
                }
                band.setName(bandName);
                band.addAlbums(Collections.singleton(album));
                bandRepo.save(band);
            }
        }
        else {
            band = bandRepo.findByName(bands);
            if (band == null) {
                band = new Band();
            }
            band.setName(bands);
            band.addAlbums(Collections.singleton(album));
            bandRepo.save(band);
        }
        Tag tag = null;
        if(tags.contains(", ")) {
            String[] tagNames = tags.split(", ");
            for (String tagName : tagNames) {
                tag = tagRepo.findByName(tagName);
                if(tag == null) {
                    tag = new Tag();
                }
                tag.setName(tagName);
                tag.addAlbums(Collections.singleton(album));
                tagRepo.save(tag);
            }
        }
        else {
            tag = tagRepo.findByName(bands);
            if (tag == null) {
                tag = new Tag();
            }
            tag.setName(bands);
            tag.addAlbums(Collections.singleton(album));
            tagRepo.save(tag);
        }


        Genre genre = null;
        if(genres.contains(", ")) {
            String[] genreNames = genres.split(", ");
            for (String genreName : genreNames) {
                genre = genreRepo.findByName(genreName);
                if(genre == null) {
                    genre = new Genre();
                }
                genre.setName(genreName);
                genre.addAlbums(Collections.singleton(album));
                genreRepo.save(genre);
            }
        }
        else {
            genre = genreRepo.findByName(bands);
            if (genre == null) {
                genre = new Genre();
            }
            genre.setName(bands);
            genre.addAlbums(Collections.singleton(album));
            genreRepo.save(genre);
        }


        if(file != null) {
            try {
                String avatarPath = storeFile(file);
                album.setAvatar(avatarPath);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        albumRepo.save(album);
        log.info("Album " + name + " has been added to database successfully");
    }

    private String storeFile(MultipartFile file) throws Exception {
        String uuidFile = UUID.randomUUID().toString();
        String resultFilename = uuidFile + "." + file.getName();

        log.info("Album avatar " + file.getName() + " now has name " + resultFilename);
        log.info("Starting uploading image " + resultFilename + " to Cloudinary storage");

        String url = CloudinaryUtils.uploadFileToCloud(file, resultFilename);

        // Check if the file's name contains invalid characters
        if (resultFilename.contains("..")) {
            log.warning("Song name " + resultFilename + " has invalid characters");
            throw new FileStorageException("Sorry! Filename contains invalid path sequence " + resultFilename);
        }
        log.info("Image " + resultFilename + " has been uploaded to Cloudinary successfully");

        return url;
    }
    /*


    public void save(Album album, String name,// String release_date,
                     String bands, String tags,
                     String genres, MultipartFile file) {
        album.setName(name);
        Band band = bandRepo.findByName(bands);
        Tag tag = tagRepo.findByName(tags);
        Genre genre = genreRepo.findByName(genres);
        album.addGenre(genre);
        album.addTag(tag);
        album.setBands(Collections.singleton(band));
        Album a2 = new Album();
            albumRepo.save(album);
    } */
    /// save songs
    public void addSongs(Album album, Map<String, String> form) {
        for (String key : form.keySet()) {
            Song nextSong = songRepo.findByPrivateName(key);
            if (nextSong != null) {
                album.addSong(nextSong);
            }
        }
        albumRepo.save(album);
    }

}
