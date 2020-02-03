package se.lexicon.amin.booklender.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import se.lexicon.amin.booklender.dto.LoanDto;
import se.lexicon.amin.booklender.service.LoanService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class LoanController {

    @Autowired
    private LoanService service;

    @GetMapping("/loans/{id}")
    public ResponseEntity<LoanDto> findById(@PathVariable int id) {
        return ResponseEntity.ok(service.findById(id));
    }

//    @GetMapping("/loans")
//    public ResponseEntity<List<LoanDto>> findAll(){
//        return ResponseEntity.ok(service.findAll());
//    }

    @PostMapping("/loans")
    public ResponseEntity<LoanDto> create(@RequestBody LoanDto dto){
        return ResponseEntity.ok(service.create(dto));
    }

    @PutMapping("/loans")
    public ResponseEntity<LoanDto> update(@RequestBody LoanDto dto){
        return ResponseEntity.ok(service.update(dto));
    }

    @GetMapping("/loans")
    public ResponseEntity<List<LoanDto>> find(@RequestParam Optional<Integer> bookId,
                                              @RequestParam Optional<Integer> userId,
                                              @RequestParam Optional<Boolean> terminated,
                                              @RequestParam(defaultValue = "true") boolean all) {

        List<LoanDto> searchResult = null;


        if(bookId.isPresent()){
            searchResult = service.findByBookId(bookId.get());
        }


        if(userId.isPresent()) {
            searchResult = service.findByUserId(userId.get());
        }


        if(terminated.isPresent())
            searchResult = service.findByTerminated(terminated.get());


        if(all && !bookId.isPresent() &&
                !userId.isPresent() &&
                !terminated.isPresent()) {
            searchResult = service.findAll();
        }

        return ResponseEntity.ok(searchResult);
        }
        }
