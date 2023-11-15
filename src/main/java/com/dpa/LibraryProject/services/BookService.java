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
import jakarta.transaction.Transactional;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
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
    
    @Transactional
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
    
    public List<Book> listBooks() {
        List<Book> books = new ArrayList();
        books = bookRepository.findAll();
        
        return books;
    }
    
    @Transactional
    public void updateBook(Long isbn, String titulo, Integer copies, String idAutor, String idEditorial) {
        Optional<Book> responseBook = bookRepository.findById(isbn);
        Optional<Author> responseAuthor = authorRepository.findById(idAutor);
        Optional<Editorial> responseEditorial = editorialRepository.findById(idEditorial);
        
        Author author = new Author();
        Editorial editorial = new Editorial();
        
        if(responseAuthor.isPresent()) {
            author = responseAuthor.get();
        }
        
        if(responseEditorial.isPresent()) {
            editorial = responseEditorial.get();
        }
        
        if(responseBook.isPresent()) {
            Book book = responseBook.get();
            book.setTitle(titulo);
            book.setAuthor(author);
            book.setEditorial(editorial);
            book.setCopies(copies);
            
            bookRepository.save(book);
        }
        
    } 
}
