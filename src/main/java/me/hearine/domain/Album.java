package me.hearine.domain;

import javax.persistence.*;
import java.util.Collections;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity(name = "album")
public class Album {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id", nullable = false, updatable = false)
    private Long id;

    private String name;
    private Date release_date;
    private String avatar; // ref
    private long totalLength;

    @ManyToMany(cascade=CascadeType.ALL)
    @JoinTable(
            name = "album_tag",
            joinColumns = { @JoinColumn(name = "album_id") },
            inverseJoinColumns = { @JoinColumn(name = "tag_id") }
    )
    private  Set<Tag> tags = new HashSet<>();

    @ManyToMany(cascade=CascadeType.ALL)
    @JoinTable(
            name = "album_band",
            joinColumns = { @JoinColumn(name = "album_id") },
            inverseJoinColumns = { @JoinColumn(name = "band_id") }
    )
    private Set<Band> bands = new HashSet<>();

    @ManyToMany(cascade=CascadeType.ALL)
    @JoinTable(
            name = "album_genre",
            joinColumns = { @JoinColumn(name = "album_id") },
            inverseJoinColumns = { @JoinColumn(name = "genre_id") }
    )
    private Set<Genre> genres = new HashSet<>();


    @ManyToMany
    @JoinTable(
            name = "song_album",
            joinColumns = { @JoinColumn(name = "album_id") },
            inverseJoinColumns = { @JoinColumn(name = "song_id") }
    )
    private Set<Song> songs = new HashSet<>();

    public Album() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getRelease_date() {
        return release_date;
    }

    public void setRelease_date(Date release_date) {
        this.release_date = release_date;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public long getTotalLength() {
        return totalLength;
    }

    public void setTotalLength(long totalLength) {
        this.totalLength = totalLength;
    }

    public Set<Tag> getTags() {
        return tags;
    }

    public void setTags(Set<Tag> tags) {
        this.tags = tags;
    }

    public void addTag(Tag tag) {
        tags.add(tag);
    }

    public Set<Genre> getGenres() {
        return genres;
    }

    public void setGenres(Set<Genre> genres) {
        this.genres = genres;
    }

    public Set<Song> getSongs() {
        return songs;
    }

    public void setSongs(Set<Song> songs) {
        this.songs = songs;
    }

    public void addSong(Song song) {
        this.songs.add(song);
    }

    public Set<Band> getBands() {
        return bands;
    }

    public void setBands(Set<Band> bands) {
        this.bands = bands;
    }

    public void addGenre(Genre genre) {
        genres.add(genre);
    }

    /* public void addTags(String[] tags) {
        String[] newArr = new String[tags.length + this.tags.length];
        System.arraycopy(this.tags, 0, newArr, 0, this.tags.length);
        System.arraycopy(tags, 0, newArr, this.tags.length, tags.length);
        tags = newArr;
    }

    public String[] getGenres() {
        return genres;
    }

    public void addGenres(String[] genres) {
        String[] newArr = new String[genres.length + this.genres.length];
        System.arraycopy(this.genres, 0, newArr, 0, this.genres.length);
        System.arraycopy(genres, 0, newArr, this.genres.length, genres.length);
        genres = newArr;
    }

    public void setGenres(String[] genres) {
        this.genres = genres;
    }

    public Set<Song> getSongs() {
        return songs;
    }

    public void setSongs(Set<Song> songs) {
        this.songs = songs;
    }

    public void addSongs(Set<Song> songs) {
        this.songs.addAll(songs);
    }

    public String[] getArtists() {
        return artists;
    }

    public void setArtists(String[] artists) {
        this.artists = artists;
    }

    public void addArtists(String[] artists)
    {
        String[] newArr = new String[artists.length + this.artists.length];
        System.arraycopy(this.artists, 0, newArr, 0, this.artists.length);
        System.arraycopy(artists, 0, newArr, this.artists.length, artists.length);
        artists = newArr;
    }

    /*public String getArtistsToString() {
        String str = "";
        for(String band : artists) {
            str += band + ", ";
        }
        return str;
    }*/
}
