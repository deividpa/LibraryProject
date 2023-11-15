package com.dpa.LibraryProject.services;

import com.dpa.LibraryProject.entities.Author;
import com.dpa.LibraryProject.repositories.AuthorRepository;
import jakarta.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
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
    
    @Transactional
    public void createAuthor(String name) {
        Author author = new Author();
        
        author.setName(name);
        authorRepository.save(author);
    }
    
    public List<Author> listAuthors() {
        List<Author> authors = new ArrayList();
        authors = authorRepository.findAll();
        
        return authors;
    }
    
    public void updateAuthor(String id, String name) {
        Optional<Author> responseAuthor = authorRepository.findById(id);
        
        if(responseAuthor.isPresent()) {
            Author author = responseAuthor.get();
            author.setName(name);
            
            authorRepository.save(author);
        }
    }
}
