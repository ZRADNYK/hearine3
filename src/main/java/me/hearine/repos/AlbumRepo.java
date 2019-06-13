package me.hearine.repos;

import me.hearine.domain.Album;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Set;

public interface AlbumRepo extends JpaRepository<Album, Long> {
    Album findByName(String name);

    Set<Album> findByNameContaining(String name);

    Set<Album> findByTags(String tags);

    Set<Album> findByGenres(String genres);

    Set<Album> findByBands(String bands);

}
