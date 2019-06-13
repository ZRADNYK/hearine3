package me.hearine.domain;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.sql.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name="band")
public class Band {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false, updatable = false)
    private long id;

    private String name;
  //  private String avatar;
    private java.sql.Date create_date;

    @ManyToMany(cascade=CascadeType.ALL)
    @JoinTable(
            name = "usr_band",
            joinColumns = { @JoinColumn(name = "band_id") },
            inverseJoinColumns = { @JoinColumn(name = "user_id") }
    )
    private Set<User> followers = new HashSet<>();

    @ManyToMany(cascade=CascadeType.ALL)
    @JoinTable(
            name = "album_band",
            joinColumns = { @JoinColumn(name = "band_id") },
            inverseJoinColumns = { @JoinColumn(name = "album_id") }
    )
    private Set<Album> albums = new HashSet<>();

    public Band() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

   /* public String getAvatar() {
        return avatar;
    }

    public Date getCreate_date() {
        return create_date;
    }

    public void setCreate_date(Date create_date) {
        this.create_date = create_date;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public Set<User> getFollowers() {
        return followers;
    }*/

    public void setFollowers(Set<User> followers) {
        this.followers = followers;
    }

    public void addFollowwer(User follower) {
        this.followers.add(follower);
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
