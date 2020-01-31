package se.lexicon.amin.booklender.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import se.lexicon.amin.booklender.dto.LoanDto;
import se.lexicon.amin.booklender.service.LoanService;

import java.util.List;

@RestController
@RequestMapping("/api")
public class LoanController {

    @Autowired
    private LoanService service;

    @GetMapping("/loans/{id}")
    public ResponseEntity<LoanDto> findById(@PathVariable int id) {
        return ResponseEntity.ok(service.findById(id));
    }

    @GetMapping("/loans")
    public ResponseEntity<List<LoanDto>> findAll(){
        return ResponseEntity.ok(service.findAll());
    }

    @PostMapping("/loans")
    public ResponseEntity<LoanDto> create(@RequestBody LoanDto dto){
        return ResponseEntity.ok(service.create(dto));
    }

    @PutMapping("/loans")
    public ResponseEntity<LoanDto> update(@RequestBody LoanDto dto){
        return ResponseEntity.ok(service.update(dto));
    }
}
