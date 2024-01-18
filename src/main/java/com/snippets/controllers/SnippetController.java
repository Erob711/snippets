package com.snippets.controllers;

import com.snippets.dtos.SnippetDto;
import com.snippets.entities.Snippet;
import com.snippets.services.SnippetService;
import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@CrossOrigin
@RequestMapping(path="/")
public class SnippetController {


    @Autowired
    private ModelMapper modelMapper;
    private final SnippetService snippetService;
    public SnippetController(SnippetService snippetService) {
        this.snippetService = snippetService;
    }



    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/snippets")
    public List<SnippetDto> getAll() {
        return snippetService.listAll().stream().map(item ->
                modelMapper.map(item, SnippetDto.class)).collect(Collectors.toList());
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/snippets/{id}")
    public ResponseEntity<SnippetDto> getById(@PathVariable Long id) {
        if (id.equals(0)) throw new IllegalArgumentException("Item id cannot be zero");
        Snippet snippet = snippetService.findById(id);
        SnippetDto snippetResponse = modelMapper.map(snippet, SnippetDto.class);
        return ResponseEntity.ok().body(snippetResponse);
    }


    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/snippets")
    public ResponseEntity<SnippetDto> createSnippet(@Valid @RequestBody SnippetDto snippetDto) {
        Snippet snippetRequest = modelMapper.map(snippetDto, Snippet.class);
        Snippet snippet = snippetService.saveSnippet(snippetRequest);
        SnippetDto snippetResponse = modelMapper.map(snippet, SnippetDto.class);

        return new ResponseEntity<SnippetDto>(snippetResponse, HttpStatus.CREATED);
    }
}
