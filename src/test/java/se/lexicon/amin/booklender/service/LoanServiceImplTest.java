package se.lexicon.amin.booklender.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import se.lexicon.amin.booklender.data.LoanRepository;
import se.lexicon.amin.booklender.dto.BookDto;
import se.lexicon.amin.booklender.dto.LibraryUserDto;
import se.lexicon.amin.booklender.dto.LoanDto;
import se.lexicon.amin.booklender.entity.Book;
import se.lexicon.amin.booklender.entity.LibraryUser;
import se.lexicon.amin.booklender.entity.Loan;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
public class LoanServiceImplTest {

    @Autowired
    private LoanService service;

    @Autowired
    private LoanRepository repository;

    private Loan testObject;
    private long testObjectId;

    private LibraryUser libraryUser;
    private Book book;

    private LoanDto testDto;
    private LibraryUserDto libraryUserDto;
    private BookDto bookDto;

    @BeforeEach
    void setup(){

        libraryUser = new LibraryUser(LocalDate.parse("2020-01-01"), "test1", "test1@email.com");
        book =  new Book("Test book 1", 11, BigDecimal.valueOf(1), "Test book 1 description");

        testObject = new Loan(libraryUser, book, LocalDate.parse("2020-01-01"), false);
        testObject = repository.save(testObject);
        testObjectId = testObject.getLoanId();

        libraryUserDto = new LibraryUserDto(0, LocalDate.parse("2020-01-01") , "dto user 1", "dto.user1@email.com");
        bookDto = new BookDto(0, "Dto book 1", true, false, 22, BigDecimal.valueOf(2), "Dto book 1 description");
        testDto = new LoanDto(0,libraryUserDto, bookDto, LocalDate.parse("2020-01-01"), true);
    }

    @Test
    public void findById_should_return_correct_dto(){
        LoanDto dto = service.findById(testObjectId);

        assertTrue(dto.getLoanId() > 0);
        assertEquals(testObjectId, dto.getLoanId());
        assertEquals(LocalDate.parse("2020-01-01"), dto.getLoanDate());
        assertEquals(false, dto.isTerminated());
        assertEquals(book.getBookId(), dto.getBook().getBookId());
        assertEquals(libraryUser.getUserId() ,dto.getLoanTaker().getUserId());

    }

    @Test
    public void findById_should_throw_when_given_nonexisting_id() {

        assertThrows(RuntimeException.class, () -> service.findById(testObjectId + 1));
    }

    @Test
    public void findAll_works_as_expected() {

        List<LoanDto> dtos = service.findAll();

        assertTrue(dtos.size() == 1);
        assertEquals(testObjectId, dtos.get(0).getLoanId());
    }

    @Test
    public void findByBookId_works_as_expected() {

        List<LoanDto> dtos = service.findByBookId(book.getBookId());

        assertTrue(dtos.size() == 1);
        assertEquals(testObjectId, dtos.get(0).getLoanId());
    }

    @Test
    public void findByUserId_works_as_expected() {

        List<LoanDto> dtos = service.findByUserId(libraryUser.getUserId());

        assertTrue(dtos.size() == 1);
        assertEquals(testObjectId, dtos.get(0).getLoanId());
    }

    @Test
    public void findByTerminated_works_as_expected() {

        List<LoanDto> dtos = service.findByTerminated(false);

        assertTrue(dtos.size() == 1);
        assertEquals(testObjectId, dtos.get(0).getLoanId());
    }




    @Test
    public void given_valid_dto_create_successful() {

        assertTrue(testDto.getLoanId() == 0);

        assertTrue(service.create(testDto).getLoanId() > 0);
    }


    @Test
    public void given_invalid_dto_create_trows_exception() {

        testDto.setLoanId(1);

        assertThrows(RuntimeException.class, () -> service.create(testDto));

    }

    @Test
    public void given_valid_dto_update_successful() {

        testDto.setLoanId(testObjectId);

        service.update(testDto);

        LoanDto updatedDto = service.findById(testObjectId);

        assertEquals(true, updatedDto.isTerminated());
        assertEquals("Dto book 1", updatedDto.getBook().getTitle());
        assertEquals("dto user 1" ,updatedDto.getLoanTaker().getName());


    }


    @Test
    public void given_invalid_dto_update_trows_exception() {

        assertThrows(RuntimeException.class, () -> service.update(testDto));

    }

    @Test
    public void test_delete_with_existing_id() {

        assertTrue(service.delete(testObjectId));
        assertThrows(RuntimeException.class, () -> service.findById(testObjectId));
    }

    @Test
    public void test_delete_with_nonexisting_id() {

        assertFalse(service.delete(testObjectId + 1000));

    }

}
