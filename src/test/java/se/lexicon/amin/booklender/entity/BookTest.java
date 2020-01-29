package se.lexicon.amin.booklender.entity;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.math.BigDecimal;


public class BookTest {

    private Book testObject;

    @BeforeEach
    void setup() {

        testObject = new Book("Test Book", 30, BigDecimal.valueOf(5.00), "A test book about nothing");
    }

    @Test
    public void object_created_successfully() {

        assertEquals(0, testObject.getBookId());
        assertEquals("Test Book", testObject.getTitle());
        assertFalse(testObject.isAvailable());
        assertFalse(testObject.isReserved());
        assertEquals(30, testObject.getMaxLoanDays());
        assertEquals(BigDecimal.valueOf(5.00), testObject.getFinePerDay());
        assertEquals("A test book about nothing", testObject.getDescription());

    }

    @Test
    public void equals_true_and_hashcode_match_when_compared_to_copy(){

        Book copy = new Book("Test Book", 30, BigDecimal.valueOf(5.00), "A test book about nothing");
        assertTrue(testObject.equals(copy));
        assertEquals(testObject.hashCode(), copy.hashCode());
    }

    @Test
    public void toString_contains_correct_information() {

        String toString = testObject.toString();

        assertTrue(toString.contains("0") &&
                toString.contains("Test Book") &&
                toString.contains("false") &&
                toString.contains("30") &&
                toString.contains("5.0") &&
                toString.contains("A test book about nothing")
        );
    }
}
