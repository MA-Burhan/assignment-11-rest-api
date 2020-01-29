package se.lexicon.amin.booklender.data;

import org.springframework.data.jpa.repository.JpaRepository;
import se.lexicon.amin.booklender.entity.Book;

public interface BookRepository extends JpaRepository<Book, Integer> {
}
