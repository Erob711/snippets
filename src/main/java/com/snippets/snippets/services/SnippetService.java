package com.snippets.snippets.services;

import com.snippets.snippets.entities.Snippet;
import com.snippets.snippets.repositories.SnippetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SnippetService {

    @Autowired
    private SnippetRepository snippetRepository;




    public List<Snippet> listAll() {
        return snippetRepository.findAll();
    }

    public Snippet findById(String id) {
        if (id.equals(0)) throw new IllegalArgumentException("Item id cannot be zero");
        System.out.printf("id is: " + id);
        Snippet snippet = (Snippet) snippetRepository.getReferenceById(id);
        return snippet;
    }

}
