/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.dpa.LibraryProject.services;

import com.dpa.LibraryProject.entities.Author;
import com.dpa.LibraryProject.entities.Book;
import com.dpa.LibraryProject.entities.Editorial;
import com.dpa.LibraryProject.repositories.AuthorRepository;
import com.dpa.LibraryProject.repositories.BookRepository;
import com.dpa.LibraryProject.repositories.EditorialRepository;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author David Perez
 */

@Service
public class BookService {
    
    @Autowired
    private BookRepository bookRepository;
    @Autowired
    private AuthorRepository authorRepository;
    @Autowired
    private EditorialRepository editorialRepository;
    
    public void createBook(Long isbn, String title, Integer copies, String idAuthor, String idEditorial) {
        
        Author author = authorRepository.findById(idAuthor).get();
        Editorial editorial = editorialRepository.findById(idEditorial).get();
        Book book = new Book();
        
        book.setIsbn(isbn);
        book.setTitle(title);
        book.setCopies(copies);
        book.setRegDate(new Date());
        book.setEditorial(editorial);
        book.setAuthor(author);
        
        bookRepository.save(book);
    }
    
    private List<Book> listBooks() {
        List<Book> books = new ArrayList();
        books = bookRepository.findAll();
        
        return books;
    }
}
