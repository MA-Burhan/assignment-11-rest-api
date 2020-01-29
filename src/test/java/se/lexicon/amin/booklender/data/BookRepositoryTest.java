package se.lexicon.amin.booklender.data;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.transaction.annotation.Transactional;
import se.lexicon.amin.booklender.entity.Book;
import se.lexicon.amin.booklender.entity.LibraryUser;

import java.math.BigDecimal;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
public class BookRepositoryTest {

    @Autowired
    private BookRepository testRepository;

    private Book testObject1;
    private int testObject1Id;

    private Book testObject2;
    private int testObject2Id;

    @BeforeEach
    void setup() {

        testObject1 = new Book("Test book 1", 11, BigDecimal.valueOf(1), "Test book 1 description");
        testObject1 = testRepository.save(testObject1);
        testObject1Id = testObject1.getBookId();

        testObject2 = new Book("Test book 2", 22, BigDecimal.valueOf(2), "Test book 2 description");
        testObject2 = testRepository.save(testObject2);
        testObject2Id = testObject2.getBookId();
    }

    @Test
    public void test_findById() {

        Book book = testRepository.findById(testObject1Id).get();

        assertEquals("Test book 1", book.getTitle());
        assertEquals(11, book.getMaxLoanDays());
        assertEquals(BigDecimal.valueOf(1), book.getFinePerDay());
        assertEquals("Test book 1 description", book.getDescription());

    }

    @Test
    public void test_findAll() {

        assertTrue(testRepository.findAll().size() == 2);
    }

    @Test
    public void test_update_book() {

        testObject1.setTitle("Updated book");

        testRepository.save(testObject1);

        Book book = testRepository.findById(testObject1Id).get();

        assertEquals("Updated book", book.getTitle());
        assertEquals(11, book.getMaxLoanDays());
        assertEquals(BigDecimal.valueOf(1), book.getFinePerDay());
        assertEquals("Test book 1 description", book.getDescription());

    }

    @Test
    public void test_delete_book() {

        testRepository.deleteById(testObject2Id);

        assertFalse(testRepository.findById(testObject2Id).isPresent());

        System.out.println("delete book" + testRepository.findAll().size());

        assertTrue(testRepository.findAll().size() == 1);

    }
}
