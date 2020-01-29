package se.lexicon.amin.booklender.data;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import se.lexicon.amin.booklender.entity.LibraryUser;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
public class LibraryUserRepositoryTest {

    @Autowired
    private LibraryUserRepository testRepository;

    private LibraryUser testObject1;
    private int testObject1Id;

    private LibraryUser testObject2;
    private int testObject2Id;

    @BeforeEach
    void setup() {
        testObject1 = new LibraryUser(LocalDate.parse("2020-01-01"), "test1", "test1@email.com");
        testObject1 = testRepository.save(testObject1);
        testObject1Id = testObject1.getUserId();

        testObject2 = new LibraryUser(LocalDate.parse("2020-01-02"), "test2", "test2@email.com");
        testObject2 = testRepository.save(testObject2);
        testObject2Id = testObject2.getUserId();
    }

    @Test
    public void test_findById() {

        LibraryUser user = testRepository.findById(testObject1Id).get();

        assertEquals(LocalDate.parse("2020-01-01"), user.getRegDate());
        assertEquals("test1", user.getName());
        assertEquals("test1@email.com", user.getEmail());

    }

    @Test
    public void test_findAll() {

        assertTrue(testRepository.findAll().size() == 2);
    }

    @Test
    public void test_update_user() {

        testObject1.setEmail("updatedtest1@email.com");

        testRepository.save(testObject1);

        LibraryUser user = testRepository.findById(testObject1Id).get();

        assertEquals(LocalDate.parse("2020-01-01"), user.getRegDate());
        assertEquals("test1", user.getName());
        assertEquals("updatedtest1@email.com", user.getEmail());

    }

    @Test
    public void test_delete_user() {

        testRepository.deleteById(testObject2Id);

        assertFalse(testRepository.findById(testObject2Id).isPresent());
        assertTrue(testRepository.findAll().size() == 1);

    }
}
