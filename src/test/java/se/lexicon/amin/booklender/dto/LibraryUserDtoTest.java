package se.lexicon.amin.booklender.dto;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class LibraryUserDtoTest {

    private LibraryUserDto testObject;

    @BeforeEach
    void setup() {
        testObject = new LibraryUserDto(1, LocalDate.parse("2020-01-01"), "Library user", "library.user@email.com");
    }

    @Test
    public void object_created_successfully() {

        assertEquals(1, testObject.getUserId());
        assertEquals(LocalDate.parse("2020-01-01"), testObject.getRegDate());
        assertEquals("Library user", testObject.getName());
        assertEquals("library.user@email.com", testObject.getEmail());
    }
}
