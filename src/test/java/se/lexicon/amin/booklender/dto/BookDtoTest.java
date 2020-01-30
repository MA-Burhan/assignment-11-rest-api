package se.lexicon.amin.booklender.dto;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class BookDtoTest {

    private BookDto testObject;

    @BeforeEach
    void setup() {

        testObject = new BookDto(
                1,
                "Test Book",
                true,
                false,
                30,
                BigDecimal.valueOf(5.00),
                "A test book about nothing");
    }

    @Test
    public void object_created_successfully() {

        assertEquals(1, testObject.getBookId());
        assertEquals("Test Book", testObject.getTitle());
        assertTrue(testObject.isAvailable());
        assertFalse(testObject.isReserved());
        assertEquals(30, testObject.getMaxLoanDays());
        assertEquals(BigDecimal.valueOf(5.00), testObject.getFinePerDay());
        assertEquals("A test book about nothing", testObject.getDescription());

    }
}
