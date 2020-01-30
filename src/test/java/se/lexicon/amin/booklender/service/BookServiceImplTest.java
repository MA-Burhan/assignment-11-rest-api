package se.lexicon.amin.booklender.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import se.lexicon.amin.booklender.data.BookRepository;
import se.lexicon.amin.booklender.dto.BookDto;
import se.lexicon.amin.booklender.entity.Book;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
public class BookServiceImplTest {

    @Autowired
    private BookService service;

    @Autowired
    private BookRepository repository;

    private Book testObject;
    private int testObjectId;

    private BookDto testDto;

    @BeforeEach
    void setup(){
        testObject = new Book("Test book 1", 11, BigDecimal.valueOf(1), "Test book 1 description");
        testObject = repository.save(testObject);
        testObjectId = testObject.getBookId();

        testDto = new BookDto(0, "Dto book 1", true, false, 22, BigDecimal.valueOf(2), "Dto book 1 description");
    }

    @Test
    public void findById_should_return_correct_dto(){
        BookDto dto = service.findById(testObjectId);

        assertTrue(dto.getBookId() > 0);
        assertEquals(testObjectId, dto.getBookId());
        assertEquals("Test book 1", dto.getTitle());
        assertEquals(11, dto.getMaxLoanDays());
        assertEquals(BigDecimal.valueOf(1), dto.getFinePerDay());
        assertEquals(false, dto.isAvailable());
        assertEquals(false, dto.isReserved());
        assertEquals("Test book 1 description", dto.getDescription());

    }

    @Test
    public void findById_should_throw_when_given_nonexisting_id() {

        assertThrows(RuntimeException.class, () -> service.findById(testObjectId + 1));
    }

    @Test
    public void findAll_works_as_expected() {

        List<BookDto> dtos = service.findAll();

        assertTrue(dtos.size() == 1);
        assertEquals(testObjectId, dtos.get(0).getBookId());
    }

    @Test
    public void findByTitle_works_as_expected() {

        List<BookDto> dtos = service.findByTitle("Test book 1");

        assertTrue(dtos.size() == 1);
        assertEquals(testObjectId, dtos.get(0).getBookId());
    }

    @Test
    public void findByAvailable_works_as_expected() {

        List<BookDto> dtos = service.findByAvailable(false);

        assertTrue(dtos.size() == 1);
        assertEquals(testObjectId, dtos.get(0).getBookId());
    }

    @Test
    public void findByReserved_works_as_expected() {

        List<BookDto> dtos = service.findByReserved(false);

        assertTrue(dtos.size() == 1);
        assertEquals(testObjectId, dtos.get(0).getBookId());
    }


    @Test
    public void given_valid_dto_create_successful() {

        assertTrue(testDto.getBookId() == 0);

        assertTrue(service.create(testDto).getBookId() > 0);
    }


    @Test
    public void given_invalid_dto_create_trows_exception() {

        testDto.setBookId(1);

        assertThrows(RuntimeException.class, () -> service.create(testDto));

    }

    @Test
    public void given_valid_dto_update_successful() {

        testDto.setBookId(testObjectId);

        service.update(testDto);

        BookDto updatedDto = service.findById(testObjectId);


        assertEquals("Dto book 1", updatedDto.getTitle());
        assertEquals(22, updatedDto.getMaxLoanDays());
        assertEquals(BigDecimal.valueOf(2), updatedDto.getFinePerDay());
        assertEquals(true, updatedDto.isAvailable());
        assertEquals("Dto book 1 description", updatedDto.getDescription());


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
