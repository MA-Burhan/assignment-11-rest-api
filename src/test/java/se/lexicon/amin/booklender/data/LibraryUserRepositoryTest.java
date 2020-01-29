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

    private LibraryUser testObject;
    private int testObjectId;


    @BeforeEach
    void setup() {
        testObject = new LibraryUser(LocalDate.parse("2020-01-01"), "test1", "test1@email.com");
        testObject = testRepository.save(testObject);
        testObjectId = testObject.getUserId();

    }

    @Test
    public void check_that_testObject_is_persisted() {


        assertTrue(testObjectId > 0);
        assertTrue(testRepository.findById(testObjectId).isPresent());

    }


    @Test
    public void test_findById() {

        LibraryUser user = testRepository.findById(testObjectId).get();

        assertEquals(testObjectId, user.getUserId());
        assertEquals(LocalDate.parse("2020-01-01"), user.getRegDate());
        assertEquals("test1", user.getName());
        assertEquals("test1@email.com", user.getEmail());

    }

    @Test
    public void test_findByEmail() {

        LibraryUser user = testRepository.findByEmail("test1@email.com").get();

        assertEquals(testObjectId, user.getUserId());
        assertEquals(LocalDate.parse("2020-01-01"), user.getRegDate());
        assertEquals("test1", user.getName());
        assertEquals("test1@email.com", user.getEmail());


    }

    @Test
    public void test_findAll() {

        assertTrue(testRepository.findAll().size() == 1);
    }

    @Test
    public void test_update_user() {

        testObject.setEmail("updatedtest1@email.com");

        testRepository.save(testObject);

        LibraryUser user = testRepository.findById(testObjectId).get();

        assertEquals(LocalDate.parse("2020-01-01"), user.getRegDate());
        assertEquals("test1", user.getName());
        assertEquals("updatedtest1@email.com", user.getEmail());

    }

    @Test
    public void test_delete_user() {

        testRepository.deleteById(testObjectId);

        assertFalse(testRepository.findById(testObjectId).isPresent());
        assertTrue(testRepository.findAll().isEmpty());

    }
}
