package me.hearine.domain;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;


@Entity
@Table(name = "playlist")
public class Playlist {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private Date create_date;
    private String lstType;
    private String lstAccess;
    private String avatar; // ref
    private String dsc;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    private User author;

    @ManyToMany(cascade=CascadeType.ALL)
    @JoinTable(
            name = "song_playlist",
            joinColumns = { @JoinColumn(name = "playlist_id") },
            inverseJoinColumns = { @JoinColumn(name = "song_id") }
    )
    private Set<Song> songs;


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

    public Date getCreate_date() {
        return create_date;
    }

    public void setCreate_date(Date create_date) {
        this.create_date = create_date;
    }

    public String getLstType() {
        return lstType;
    }

    public void setLstType(String lstType) {
        this.lstType = lstType;
    }

    public String getDsc() {
        return dsc;
    }

    public void setDsc(String dsc) {
        this.dsc = dsc;
    }

    public User getAuthor() {
        return author;
    }

    public void setAuthor(User author) {
        this.author = author;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
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

    public String getLstAccess() {
        return lstAccess;
    }

    public void setLstAccess(String lstAccess) {
        this.lstAccess = lstAccess;
    }

    public Playlist() {}

     /*public Playlist(String name, Date create_date, String lstType, String access_level) {
        this.name = name;
        this.create_date = create_date;
        this.lstType = lstType;
        this.access_level = access_level;
      //  this.songs = songs;
    }

    public Playlist(String name, Date create_date, String lstType, String access_level, String dsc, HashSet<User> user) {
        this.name = name;
        this.create_date = create_date;
        this.lstType = lstType;
        this.access_level = access_level;
        this.dsc = dsc;
        this.subscribers = user;
      //  this.songs = songs;
    }

    public Playlist(String name, String lstType) {
        this.name = name;
        this.lstType = lstType;
    }
    public Playlist() {}

  /*  public Song[] getSongs() {
        return songs;
    }

    public void setSongs(Song[] songs) {
        this.songs = songs;
    }

    @Override
    public String toString() {
        return  "Name: " + name + "\n" + "Songs: " + songs.toString();
        /*for (Song song : songs) {
            System.out.print(song.getPublicName() + ", ");
            System.out.println();
        }*/

}