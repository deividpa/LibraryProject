package com.dpa.LibraryProject.services;

import com.dpa.LibraryProject.entities.Author;
import com.dpa.LibraryProject.repositories.AuthorRepository;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author David Perez
 */

@Service
public class AuthorService {
    
    @Autowired
    private AuthorRepository authorRepository;
    
    private void createAuthor(String name) {
        Author author = new Author();
        
        author.setName(name);
        authorRepository.save(author);
    }
    
    private List<Author> listAuthors() {
        List<Author> authors = new ArrayList();
        authors = authorRepository.findAll();
        
        return authors;
    }
    
}
