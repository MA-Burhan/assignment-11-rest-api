package se.lexicon.amin.booklender.data;

import org.springframework.data.jpa.repository.JpaRepository;
import se.lexicon.amin.booklender.entity.Book;

import java.util.List;
import java.util.Optional;

public interface BookRepository extends JpaRepository<Book, Integer> {

    Optional<Book> findByTitle(String title);

    List<Book> findByAvailable(boolean available);

    List<Book> findByReserved(boolean reserved);
}
