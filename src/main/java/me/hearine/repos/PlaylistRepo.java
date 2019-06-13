package me.hearine.repos;

import me.hearine.domain.Playlist;
import me.hearine.domain.Song;
import me.hearine.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Set;

public interface PlaylistRepo extends JpaRepository<Playlist, Long> {
    Playlist findByName(String name);

    List<Playlist> findByNameContaining(String name);

    List<Playlist> findByLstTypeContaining(String typeName);

    List<Playlist> findByDscContaining(String dsc);

    List<Playlist> findByAuthorAndLstAccess(User author, String access);

    List<Playlist> findByLstAccess(String access);

    List<Playlist> findByNameContainingAndLstAccess(String name, String lstAccess);

}
