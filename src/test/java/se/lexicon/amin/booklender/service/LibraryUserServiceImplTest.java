package se.lexicon.amin.booklender.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import se.lexicon.amin.booklender.data.LibraryUserRepository;
import se.lexicon.amin.booklender.dto.LibraryUserDto;
import se.lexicon.amin.booklender.entity.LibraryUser;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
public class LibraryUserServiceImplTest {

    @Autowired
    private LibraryUserService service;

    @Autowired
    private LibraryUserRepository repository;

    private LibraryUser testObject;
    private int testObjectId;

    private LibraryUserDto testDto;

    @BeforeEach
    void setup(){
        testObject = new LibraryUser(LocalDate.parse("2020-01-01"), "test user 1", "test.user1@email.com");
        testObject = repository.save(testObject);
        testObjectId = testObject.getUserId();

        testDto = new LibraryUserDto(0, LocalDate.parse("2020-01-01") , "dto user 1", "dto.user1@email.com");
    }
    @Test
    public void findById_should_return_correct_dto(){
        LibraryUserDto dto = service.findById(testObjectId);

        assertTrue(dto.getUserId() > 0);
        assertEquals(testObjectId, dto.getUserId());
        assertEquals("test.user1@email.com", dto.getEmail());
        assertEquals("test user 1", dto.getName());
        assertEquals(LocalDate.parse("2020-01-01"), dto.getRegDate());
    }

    @Test
    public void findById_should_throw_when_given_nonexisting_id() {

        assertThrows(RuntimeException.class, () -> service.findById(testObjectId + 1));
    }

    @Test
    public void findByEmail_should_return_correct_dto(){
        LibraryUserDto dto = service.findByEmail("test.user1@email.com");

        assertTrue(dto.getUserId() > 0);
        assertEquals(testObjectId, dto.getUserId());
        assertEquals("test.user1@email.com", dto.getEmail());
        assertEquals("test user 1", dto.getName());
        assertEquals(LocalDate.parse("2020-01-01"), dto.getRegDate());
    }

    @Test
    public void findByEmail_should_throw_when_given_nonexisting_email() {

        assertThrows(RuntimeException.class, () -> service.findByEmail("abcdefg@email.com"));
    }

    @Test
    public void findAll_works_as_expected() {

        List<LibraryUserDto> dtos = service.findAll();

        assertTrue(dtos.size() == 1);
        assertEquals(testObjectId, dtos.get(0).getUserId());
    }


    @Test
    public void given_valid_dto_create_successful() {

        assertTrue(testDto.getUserId() == 0);

        assertTrue(service.create(testDto).getUserId() > 0);
    }


    @Test
    public void given_invalid_dto_create_trows_exception() {

        testDto.setUserId(1);

        assertThrows(RuntimeException.class, () -> service.create(testDto));

    }

    @Test
    public void given_valid_dto_update_successful() {

        testDto.setUserId(testObjectId);

        service.update(testDto);

        LibraryUserDto updatedDto = service.findById(testObjectId);

        assertEquals("dto.user1@email.com", updatedDto.getEmail());
        assertEquals("dto user 1", updatedDto.getName());


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
