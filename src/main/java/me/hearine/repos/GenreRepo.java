package me.hearine.repos;

import me.hearine.domain.Genre;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GenreRepo extends JpaRepository<Genre, Long> {

    Genre findByName(String name);


}
