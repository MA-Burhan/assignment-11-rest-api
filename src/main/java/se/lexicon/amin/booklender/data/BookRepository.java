package se.lexicon.amin.booklender.data;

import org.springframework.data.jpa.repository.JpaRepository;
import se.lexicon.amin.booklender.entity.Book;

import java.util.List;

public interface BookRepository extends JpaRepository<Book, Integer> {

    List<Book> findByTitle(String title);

    List<Book> findByAvailable(boolean available);

    List<Book> findByReserved(boolean reserved);
}
