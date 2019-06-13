package me.hearine.domain;

import javax.persistence.*;
import java.sql.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "song")
public class Song {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    private String publicName;
    private String privateName;
    private String length;

    private String fileType;
    private String songPath;

    @ManyToMany(cascade=CascadeType.ALL)
    @JoinTable(
            name = "song_playlist",
            joinColumns = { @JoinColumn(name = "song_id") },
            inverseJoinColumns = { @JoinColumn(name = "playlist_id") }
    )
    private Set<Playlist> addedToPlaylists = new HashSet<>();

    @ManyToMany(cascade=CascadeType.ALL)
    @JoinTable(
            name = "song_album",
            joinColumns = { @JoinColumn(name = "song_id") },
            inverseJoinColumns = { @JoinColumn(name = "album_id") }
    )
    private Set<Album> addedToAlbums = new HashSet<>();

    @ManyToMany(cascade=CascadeType.ALL)
    @JoinTable(
            name = "song_genre",
            joinColumns = { @JoinColumn(name = "song_id") },
            inverseJoinColumns = { @JoinColumn(name = "genre_id") }
    )
    private Set<Genre> genres = new HashSet<>();

    @ManyToMany(cascade=CascadeType.ALL)
    @JoinTable(
            name = "song_tag",
            joinColumns = { @JoinColumn(name = "song_id") },
            inverseJoinColumns = { @JoinColumn(name = "tag_id") }
    )
    private Set<Tag> tags = new HashSet<>();

    public Song() {
    }

    public Song(String publicName, String resultFilename, String contentType, String path) {
        this.publicName = publicName;
        this.privateName = resultFilename;
        //     this.releaseDate = new Date(1970- 1 - 1);
        this.fileType = contentType;
        this.length = String.valueOf(-1);
        this.songPath = path;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPublicName() {
        return publicName;
    }

    public void setPublicName(String publicName) {
        this.publicName= publicName;
    }

    public String getLength() {
        return length;
    }

    public void setLength(String length) {
        this.length = length;
    }

    public String getSongPath() {
        return songPath;
    }

    public void setSongPath(String songPath) {
        this.songPath = songPath;
    }

    public String getPrivateName() {
        return privateName;
    }

    public void setPrivateName(String privateName) {
        this.privateName = privateName;
    }

    public String getFileType() {
        return fileType;
    }

    public void setFileType(String fileType) {
        this.fileType = fileType;
    }

    public Set<Playlist> getAddedToPlaylists() {
        return addedToPlaylists;
    }

    public void setAddedToPlaylists(Set<Playlist> addedToPlaylists) {
        this.addedToPlaylists = addedToPlaylists;
    }

    public void addToPlaylist(Playlist playlist) {
        addedToPlaylists.add(playlist);
    }

    public Set<Album> getAddedToAlbums() {
        return addedToAlbums;
    }

    public void setAddedToAlbums(Set<Album> addedToAlbums) {
        this.addedToAlbums = addedToAlbums;
    }

    public void addAddedToAlbums(Set<Album> addedToAlbums) {
        this.addedToAlbums.addAll(addedToAlbums);
    }

    public Set<Genre> getGenres() {
        return genres;
    }

    public void setGenres(Set<Genre> genres) {
        this.genres = genres;
    }

    public void addGenres(Set<Genre> genres) {
        this.genres.addAll(genres);
    }

    public Set<Tag> getTags() {
        return tags;
    }

    public void setTags(Set<Tag> tags) {
        this.tags = tags;
    }

    public void addTags(Set<Tag> tags) {
        this.tags.addAll(tags);
    }
}