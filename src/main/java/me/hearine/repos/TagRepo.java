package me.hearine.repos;

import me.hearine.domain.Tag;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TagRepo extends JpaRepository<Tag, Long> {

    Tag findByName(String name);


}
