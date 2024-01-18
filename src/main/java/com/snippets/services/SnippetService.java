package com.snippets.services;

import com.snippets.repositories.SnippetRepository;
import com.snippets.entities.Snippet;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SnippetService {

    @Autowired
    private SnippetRepository snippetRepository;

    public SnippetService() {

    }

    public List<Snippet> listAll() {
        return snippetRepository.findAll();
    }

    public Snippet findById(Long id) {
        if (id == 0) throw new IllegalArgumentException("Item id cannot be zero");
        System.out.printf("id is: " + id);
        Snippet snippet = snippetRepository.getReferenceById(id);
        return snippet;
    }

    public Snippet saveSnippet(@Valid Snippet snippet) {
//        Set<ConstraintViolation<Snippet>> violations = validator.validate(item);
//        for (ConstraintViolation<Item> violation : violations) {
//            throw new IllegalArgumentException((violation.getMessage()));
//        }
//        System.out.println("item to be posted: " + item);
        Snippet newSnippet = new Snippet();
        try {
            newSnippet = snippetRepository.save(snippet);
            System.out.println("newSnippet: " + newSnippet);
        } catch (Exception e) {
            throw new IllegalArgumentException("Something wrong with snippet to be saved.");
        }
        return newSnippet;
    }

}
