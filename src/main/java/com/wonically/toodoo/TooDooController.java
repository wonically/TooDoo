package com.wonically.toodoo;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/toodoo")
public class TooDooController {
    private final TooDooRepository tooDooRepository;

    public TooDooController(TooDooRepository tooDooRepository) {
        this.tooDooRepository = tooDooRepository;
    }

    @GetMapping
    public List<TooDoo> getAllTooDoos() {
        return tooDooRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<TooDoo> getTooDooById(@PathVariable Long id) {
        return tooDooRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<TooDoo> createTooDoo(@RequestBody TooDoo tooDoo) {
        TooDoo savedTooDoo = tooDooRepository.save(tooDoo);
        return new ResponseEntity<>(savedTooDoo, HttpStatus.CREATED);

    }
}
