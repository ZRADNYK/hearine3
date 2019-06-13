package me.hearine.repos;


import me.hearine.domain.*;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.Set;

public interface SongRepo extends JpaRepository<Song, Long> {

    Set<Song> findByPublicName(String publicName);

    Set<Song> findByPublicNameContaining(String publicName);

    Song findByPrivateName(String privateName);

    Song findBySongPath(String songPath);

    Set<Song> findSongsByAddedToPlaylists(Playlist playlist);

    Set<Song> findByTags(Tag tag);

    Set<Song> findByGenres(Genre genre);

    Set<Song> findSongsByAddedToPlaylistsIsNotContaining(Optional<Playlist> byId);

    Set<Song> findSongsByAddedToAlbumsIsNotContaining(Optional<Album> byId);
}
