package com.snippets.snippets.controllers;

import com.snippets.snippets.services.SnippetService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SnippetController {


    @Autowired
    private ModelMapper modelMapper;
    private final SnippetService snippetService;
    public SnippetController(SnippetService snippetService) {
        this.snippetService = snippetService;
    }



}
