package se.lexicon.amin.booklender.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import se.lexicon.amin.booklender.dto.BookDto;
import se.lexicon.amin.booklender.service.BookService;

import java.util.List;

@RestController
@RequestMapping("/api")
public class BookController {

    @Autowired
    private BookService service;

    @GetMapping("/books/{id}")
    public ResponseEntity<BookDto> findById(@PathVariable int id) {
        return ResponseEntity.ok(service.findById(id));
    }

    @GetMapping("/books")
    public ResponseEntity<List<BookDto>> findAll(){
        return ResponseEntity.ok(service.findAll());
    }

    @PostMapping("/books")
    public ResponseEntity<BookDto> create(@RequestBody BookDto dto){
        return ResponseEntity.ok(service.create(dto));
    }

    @PutMapping("/books")
    public ResponseEntity<BookDto> update(@RequestBody BookDto dto){
        return ResponseEntity.ok(service.update(dto));
    }
}
