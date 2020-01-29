package se.lexicon.amin.booklender.entity;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;

public class LibraryUserTest {

    private LibraryUser testObject;

    @BeforeEach
    void setup() {
        testObject = new LibraryUser(LocalDate.parse("2020-01-01"), "Library user", "library.user@email.com");
    }

    @Test
    public void object_created_successfully() {
        assertEquals(0, testObject.getUserId());
        assertEquals(LocalDate.parse("2020-01-01"), testObject.getRegDate());
        assertEquals("Library user", testObject.getName());
        assertEquals("library.user@email.com", testObject.getEmail());
    }

    @Test
    public void object_created_with_null_date_throws_exception(){
        assertThrows(IllegalArgumentException.class, () -> new LibraryUser(null, "Library user", "library.user@email.com" ));
    }

    @Test
    public void equals_true_and_hashcode_match_when_compared_to_copy(){
        LibraryUser copy = new LibraryUser(LocalDate.parse("2020-01-01"), "Library user", "library.user@email.com");
        assertTrue(testObject.equals(copy));
        assertEquals(testObject.hashCode(), copy.hashCode());
    }

    @Test
    public void toString_contains_correct_information(){

        String toString = testObject.toString();

        System.out.println(toString);

        assertTrue(toString.contains("0") &&
                toString.contains("2020-01-01") &&
                toString.contains("Library user") &&
                toString.contains("library.user@email.com")
        );
    }

}
