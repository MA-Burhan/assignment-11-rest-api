package se.lexicon.amin.booklender.data;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import se.lexicon.amin.booklender.entity.Book;
import se.lexicon.amin.booklender.entity.LibraryUser;
import se.lexicon.amin.booklender.entity.Loan;

import java.math.BigDecimal;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
public class LoanRepositoryTest {

    @Autowired
    private LoanRepository testRepository;

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private LibraryUserRepository libraryUserRepository;

    private Loan testObject;
    private long testObjectId;

    private LibraryUser libraryUser;
    private Book book;

    @BeforeEach
    void setup() {

       libraryUser = new LibraryUser(LocalDate.parse("2020-01-01"), "test1", "test1@email.com");
       book =  new Book("Test book 1", 11, BigDecimal.valueOf(1), "Test book 1 description");

       testObject = new Loan(libraryUser, book, LocalDate.parse("2020-01-01"), false);
       testObject = testRepository.save(testObject);
       testObjectId = testObject.getLoanId();
    }

    @Test
    public void check_that_testObject_is_persisted() {


        assertTrue(testObjectId > 0);
        assertTrue(testRepository.findById(testObjectId).isPresent());

    }

    @Test
    public void check_that_libraryUser_is_persisted(){

        int id = libraryUser.getUserId();
        assertTrue(id > 0);
        assertTrue(libraryUserRepository.findById(id).isPresent());
    }

    @Test
    public void check_that_book_is_persisted(){

        int id = book.getBookId();
        assertTrue(id > 0);
        assertTrue(bookRepository.findById(id).isPresent());
    }

    @Test
    public void test_findById() {

        Loan loan = testRepository.findById(testObjectId).get();

        assertEquals(testObjectId, loan.getLoanId());
        assertEquals(libraryUser, loan.getLoanTaker());
        assertEquals(book, loan.getBook());
        assertEquals(LocalDate.parse("2020-01-01"), loan.getLoanDate());
        assertFalse(loan.isTerminated());

    }

    @Test
    public void test_findAll() {

        assertTrue(testRepository.findAll().size() == 1);
    }

    @Test
    public void test_update_load() {

        testObject.setTerminated(true);

        testRepository.save(testObject);

        Loan loan = testRepository.findById(testObjectId).get();

        assertEquals(libraryUser, loan.getLoanTaker());
        assertEquals(book, loan.getBook());
        assertEquals(LocalDate.parse("2020-01-01"), loan.getLoanDate());
        assertTrue(loan.isTerminated());

    }

    @Test
    public void test_delete_user() {

        testRepository.deleteById(testObjectId);

        assertFalse(testRepository.findById(testObjectId).isPresent());
        assertTrue(testRepository.findAll().isEmpty());

    }


}
