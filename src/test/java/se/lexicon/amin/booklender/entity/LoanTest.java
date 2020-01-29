package se.lexicon.amin.booklender.entity;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.math.BigDecimal;
import java.time.LocalDate;

public class LoanTest {

    private Loan testObject;
    private Book testBook;
    private LibraryUser testLibraryUser;

    private LocalDate lastReturnDate;

    @BeforeEach
    void setup() {

        testBook = new Book("Test Book", 30, BigDecimal.valueOf(5.00), "A test book about nothing");
        testLibraryUser = new LibraryUser(LocalDate.parse("2020-01-01"), "Library user", "library.user@email.com");
        testObject = new Loan(testLibraryUser, testBook, LocalDate.parse("2020-01-01"), false);

        lastReturnDate = LocalDate.parse("2020-01-01").plusDays(30);
    }

    @Test
    public void object_created_successfully() {

        assertEquals(0, testObject.getLoanId());
        assertEquals(testLibraryUser, testObject.getLoanTaker());
        assertEquals(testBook, testObject.getBook());
        assertEquals(LocalDate.parse("2020-01-01"), testObject.getLoanDate());
        assertFalse(testObject.isTerminated());
        assertEquals(lastReturnDate, testObject.getLastReturnDate());
    }

    @Test
    public void equals_true_and_hashcode_match_when_compared_to_copy(){

        Loan copy = new Loan(testLibraryUser, testBook, LocalDate.parse("2020-01-01"), false);
        assertTrue(testObject.equals(copy));
        assertEquals(testObject.hashCode(), copy.hashCode());
    }

    @Test
    public void toString_contains_correct_information() {

        String toString = testObject.toString();

        System.out.println(toString);

        assertTrue(toString.contains("0") &&
                toString.contains(testLibraryUser.toString()) &&
                toString.contains(testBook.toString()) &&
                toString.contains("2020-01-01") &&
                toString.contains("false") &&
                toString.contains("2020-01-31")
        );
    }

    @Test
    public void isOverdue_should_return_true() {

        LocalDate fiveDaysAgo = LocalDate.now().minusDays(5);
        testObject.setLastReturnDate(fiveDaysAgo);

        assertTrue(testObject.isOverdue());
    }

    @Test
    public void isOverdue_should_return_false() {

        LocalDate fiveDaysAhead = LocalDate.now().plusDays(5);
        testObject.setLastReturnDate(fiveDaysAhead);

        assertFalse(testObject.isOverdue());
    }

    @Test
    public void getFine_should_return_correct_amount() {

        LocalDate fiveDaysAgo = LocalDate.now().minusDays(5);
        testObject.setLastReturnDate(fiveDaysAgo);

        double calculatedFine = testObject.getFine().doubleValue();

        assertEquals(25.00, calculatedFine );
    }

    @Test
    public void getFine_should_return_zero() {

        LocalDate fiveDaysAhead = LocalDate.now().plusDays(5);
        testObject.setLastReturnDate(fiveDaysAhead);

        double calculatedFine = testObject.getFine().doubleValue();

        assertEquals(0.00, calculatedFine );
    }

    @Test
    public void extendLoan_works_as_expected() {

        LocalDate date1 = LocalDate.parse("2020-02-01");
        LocalDate date2 = date1.plusDays(10);
        testObject.setLastReturnDate(date1);

        assertEquals(date1, testObject.getLastReturnDate());

        testObject.extendLoan(10);

        assertEquals(date2, testObject.getLastReturnDate());
    }
}
