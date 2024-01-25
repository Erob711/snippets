package com.snippets.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.snippets.dtos.SnippetDto;
import com.snippets.entities.Snippet;
import com.snippets.services.SnippetService;
import jakarta.annotation.security.PermitAll;
import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ResourceLoader;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.encrypt.Encryptors;
import org.springframework.security.crypto.encrypt.TextEncryptor;
import org.springframework.web.bind.annotation.*;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@PermitAll
@RestController
@CrossOrigin
@RequestMapping(path="/")
public class SnippetController {


    @Autowired
    ResourceLoader resourceLoader;
    @Autowired
    private ModelMapper modelMapper;
    private final SnippetService snippetService;

    @Value("${encryption.password}")
    private String encryptionPass;

    @Value("${encryption.salt}")
    private String encryptionSalt;
    public SnippetController(SnippetService snippetService) {
        this.snippetService = snippetService;
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/snippets")
    public ResponseEntity<Snippet[]> getAll() throws IOException {

        File snippetResource = resourceLoader.getResource("classpath:seedData.json").getFile();
        System.out.print("snippet resource: " + snippetResource);
        ObjectMapper mapper = new ObjectMapper();
        Snippet[] snippets = mapper.readValue(snippetResource, Snippet[].class);
//        TextEncryptor encryptor = Encryptors.text(encryptionPass, encryptionSalt);
//
//
//        for (int i = 0; i < snippets.length; i++) {
//            String decryptedCode = encryptor.decrypt(snippets[i].getCode());
//            snippets[i].setCode(decryptedCode);
//        }

        return new ResponseEntity<>(snippets, HttpStatus.OK);
        //JpaRespository implementation: for when linked to "real" db
//        return snippetService.listAll().stream().map(item ->
//                modelMapper.map(item, SnippetDto.class)).collect(Collectors.toList());
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
