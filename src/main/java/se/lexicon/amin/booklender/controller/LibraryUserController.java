package se.lexicon.amin.booklender.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import se.lexicon.amin.booklender.dto.LibraryUserDto;
import se.lexicon.amin.booklender.service.LibraryUserService;

import java.util.List;

@RestController
@RequestMapping("/api")
public class LibraryUserController {

    @Autowired
    private LibraryUserService service;

    @GetMapping("/users/{id}")
    public ResponseEntity<LibraryUserDto> findById(@PathVariable int id) {
        return ResponseEntity.ok(service.findById(id));
    }

    @GetMapping("/users")
    public ResponseEntity<List<LibraryUserDto>> findAll(){
        return ResponseEntity.ok(service.findAll());
    }

    @PostMapping("/users")
    public ResponseEntity<LibraryUserDto> create(@RequestBody LibraryUserDto dto){
        return ResponseEntity.ok(service.create(dto));
    }

    @PutMapping("/users")
    public ResponseEntity<LibraryUserDto> update(@RequestBody LibraryUserDto dto){
        return ResponseEntity.ok(service.update(dto));
    }
}
