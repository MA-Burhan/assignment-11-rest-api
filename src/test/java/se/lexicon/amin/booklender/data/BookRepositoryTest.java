package se.lexicon.amin.booklender.data;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import se.lexicon.amin.booklender.entity.Book;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
public class BookRepositoryTest {

    @Autowired
    private BookRepository testRepository;

    private Book testObject;
    private int testObjectId;



    @BeforeEach
    void setup() {

        testObject = new Book("Test book 1", 11, BigDecimal.valueOf(1), "Test book 1 description");
        testObject = testRepository.save(testObject);
        testObjectId = testObject.getBookId();

    }

    @Test
    public void check_that_testObject_is_persisted() {


        assertTrue(testObjectId > 0);
        assertTrue(testRepository.findById(testObjectId).isPresent());

    }

    @Test
    public void test_findById() {

        Book book = testRepository.findById(testObjectId).get();

        assertEquals("Test book 1", book.getTitle());
        assertEquals(11, book.getMaxLoanDays());
        assertEquals(BigDecimal.valueOf(1), book.getFinePerDay());
        assertEquals("Test book 1 description", book.getDescription());

    }

    @Test
    public void test_findAll() {

        assertTrue(testRepository.findAll().size() == 1);
    }

    @Test
    public void test_update_book() {

        testObject.setTitle("Updated book");

        testRepository.save(testObject);

        Book book = testRepository.findById(testObjectId).get();

        assertEquals("Updated book", book.getTitle());
        assertEquals(11, book.getMaxLoanDays());
        assertEquals(BigDecimal.valueOf(1), book.getFinePerDay());
        assertEquals("Test book 1 description", book.getDescription());

    }

    @Test
    public void test_delete_book() {

        testRepository.deleteById(testObjectId);

        assertFalse(testRepository.findById(testObjectId).isPresent());

        assertTrue(testRepository.findAll().isEmpty());

    }
}
