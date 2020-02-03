package se.lexicon.amin.booklender.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import se.lexicon.amin.booklender.dto.BookDto;
import se.lexicon.amin.booklender.service.BookService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class BookController {

    @Autowired
    private BookService service;

    @GetMapping("/books/{id}")
    public ResponseEntity<BookDto> findById(@PathVariable int id) {
        return ResponseEntity.ok(service.findById(id));
    }

//    @GetMapping("/books")
//    public ResponseEntity<List<BookDto>> findAll(){
//        return ResponseEntity.ok(service.findAll());
//    }

    @PostMapping("/books")
    public ResponseEntity<BookDto> create(@RequestBody BookDto dto){
        return ResponseEntity.ok(service.create(dto));
    }

    @PutMapping("/books")
    public ResponseEntity<BookDto> update(@RequestBody BookDto dto){
        return ResponseEntity.ok(service.update(dto));
    }

    @GetMapping("/books")
    public ResponseEntity<List<BookDto>> find(@RequestParam Optional<String> title,
                                              @RequestParam Optional<Boolean> available,
                                              @RequestParam Optional<Boolean> reserved,
                                              @RequestParam(defaultValue = "true") boolean all) {

        List<BookDto> searchResult = null;

        if(title.isPresent()){
            searchResult = service.findByTitle(title.get());
        }


        if(available.isPresent()) {
            searchResult = service.findByAvailable(available.get());
        }


        if(reserved.isPresent())
            searchResult = service.findByReserved(reserved.get());


        if(all && !title.isPresent() &&
                !available.isPresent() &&
                !reserved.isPresent()) {
            searchResult = service.findAll();
        }

        return ResponseEntity.ok(searchResult);
    }
}
