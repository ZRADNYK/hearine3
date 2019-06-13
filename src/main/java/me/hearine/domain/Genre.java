package me.hearine.domain;


import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "genre")
public class Genre {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @ManyToMany(cascade=CascadeType.ALL)
    @JoinTable(
            name = "song_genre",
            joinColumns = { @JoinColumn(name = "genre_id") },
            inverseJoinColumns = { @JoinColumn(name = "song_id") }
    )
    private Set<Song> songs = new HashSet<>();

    @ManyToMany(cascade=CascadeType.ALL)
    @JoinTable(
            name = "album_genre",
            joinColumns = { @JoinColumn(name = "genre_id") },
            inverseJoinColumns = { @JoinColumn(name = "album_id") }
    )
    private Set<Album> albums = new HashSet<>();

    public Genre() {
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

    public Set<Song> getSongs() {
        return songs;
    }

    public void setSongs(Set<Song> songs) {
        this.songs = songs;
    }

    public void addSongs(Set<Song> songs) {
        this.songs.addAll(songs);
    }

    public Set<Album> getAlbums() {
        return albums;
    }

    public void setAlbums(Set<Album> albums) {
        this.albums = albums;
    }

    public void addAlbums(Set<Album> albums) {
        this.albums.addAll(albums);
    }
}
