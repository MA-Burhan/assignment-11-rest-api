package se.lexicon.amin.booklender.data;

import org.springframework.data.jpa.repository.JpaRepository;
import se.lexicon.amin.booklender.entity.LibraryUser;

public interface LibraryUserRepository extends JpaRepository<LibraryUser, Integer> {
}
