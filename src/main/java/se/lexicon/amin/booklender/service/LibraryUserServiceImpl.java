package se.lexicon.amin.booklender.service;

import org.springframework.beans.factory.annotation.Autowired;
import se.lexicon.amin.booklender.data.LibraryUserRepository;
import se.lexicon.amin.booklender.dto.LibraryUserDto;
import se.lexicon.amin.booklender.entity.LibraryUser;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class LibraryUserServiceImpl implements LibraryUserService {

    @Autowired
    private LibraryUserRepository repository;

    @Autowired
    private DtoEntityConversionService conversionService;

    @Override
    public LibraryUserDto findById(int userId) {
        Optional<LibraryUser> optionalLibraryUser = repository.findById(userId);

        LibraryUser libraryUser = optionalLibraryUser.orElseThrow(() -> new RuntimeException("Requested Library user does not exist"));

        return conversionService.libraryUserToDto(libraryUser);
    }

    @Override
    public LibraryUserDto findByEmail(String email) {
        Optional<LibraryUser> optionalLibraryUser = repository.findByEmail(email);

        LibraryUser libraryUser = optionalLibraryUser.orElseThrow(() -> new RuntimeException("Requested Library user does not exist"));

        return conversionService.libraryUserToDto(libraryUser);
    }

    @Override
    public List<LibraryUserDto> findAll() {
        List<LibraryUser> libraryUsers = repository.findAll();

        List<LibraryUserDto> libraryUserDtos = libraryUsers.stream().map(conversionService::libraryUserToDto).collect(Collectors.toList());

        return libraryUserDtos;
    }

    @Override
    public LibraryUserDto create(LibraryUserDto libraryUserDto) {
        if(libraryUserDto.getUserId() != 0){
            throw new IllegalArgumentException("Library user had invalid id: " + libraryUserDto.getUserId());
        }

        LibraryUser newLibraryUser = conversionService.dtoToLibraryUser(libraryUserDto);

        newLibraryUser = repository.save(newLibraryUser);
        return conversionService.libraryUserToDto(newLibraryUser);
    }

    @Override
    public LibraryUserDto update(LibraryUserDto libraryUserDto) {
        if(libraryUserDto.getUserId() == 0){
            throw new IllegalArgumentException("Library user had invalid id: " + libraryUserDto.getUserId());
        }

        Optional<LibraryUser> optionalLibraryUser = repository.findById(libraryUserDto.getUserId());
        LibraryUser libraryUser = optionalLibraryUser.orElseThrow(() -> new RuntimeException("Requested Library user does not exist"));

        libraryUser.setEmail(libraryUserDto.getEmail());
        libraryUser.setName(libraryUserDto.getName());

        repository.save(libraryUser);

        return libraryUserDto;
    }

    @Override
    public boolean delete(int userId) {
        Optional<LibraryUser> optionalLibraryUser = repository.findById(userId);

        if(optionalLibraryUser.isPresent()) {
            repository.deleteById(userId);
            return true;
        }
        return false;
    }
}
