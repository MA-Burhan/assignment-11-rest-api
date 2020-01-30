package se.lexicon.amin.booklender.dto;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

public class LoanDtoTest {

    private LoanDto testObject;
    private BookDto testBook;
    private LibraryUserDto testLibraryUser;

    private LocalDate lastReturnDate;

    @BeforeEach
    void setup() {

        testBook = new BookDto(
                1,
                "Test Book",
                true,
                false,
                30,
                BigDecimal.valueOf(5.00),
                "A test book about nothing");

        testLibraryUser = new LibraryUserDto(
                1,
                LocalDate.parse("2020-01-01"),
                "Library user",
                "library.user@email.com");

        testObject = new LoanDto(
                1,
                testLibraryUser,
                testBook,
                LocalDate.parse("2020-01-01"),
                false);


        lastReturnDate = LocalDate.parse("2020-01-01").plusDays(30);
    }

    @Test
    public void object_created_successfully() {

        assertEquals(1, testObject.getLoanId());
        assertEquals(testLibraryUser, testObject.getLoanTaker());
        assertEquals(testBook, testObject.getBook());
        assertEquals(LocalDate.parse("2020-01-01"), testObject.getLoanDate());
        assertFalse(testObject.isTerminated());
        assertEquals(lastReturnDate, testObject.getLastReturnDate());
    }
}
