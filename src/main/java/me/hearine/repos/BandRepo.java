package me.hearine.repos;

import me.hearine.domain.Album;
import me.hearine.domain.Band;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Set;

public interface BandRepo extends JpaRepository<Band, Long> {
    Band findByName(String name);

    Set<Band> findByAlbumsContaining(Album album);

}
