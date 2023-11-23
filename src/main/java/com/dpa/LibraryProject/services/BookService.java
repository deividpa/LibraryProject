package com.dpa.LibraryProject.services;

import com.dpa.LibraryProject.entities.Author;
import com.dpa.LibraryProject.entities.Book;
import com.dpa.LibraryProject.entities.Editorial;
import com.dpa.LibraryProject.exceptions.MyException;
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
    public void createBook(Long isbn, String title, Integer copies, String idAuthor, String idEditorial) throws MyException {
        
        validate(isbn, title, copies, idAuthor, idEditorial);
        
        Book book = new Book();
        
        Author author = authorRepository.findById(idAuthor).get();
        Editorial editorial = editorialRepository.findById(idEditorial).get();
        
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
    public void updateBook(Long isbn, String title, Integer copies, String idAuthor, String idEditorial) throws MyException {
        
        validate(isbn, title, copies, idAuthor, idEditorial);
        
        Optional<Book> responseBook = bookRepository.findById(isbn);
        Optional<Author> responseAuthor = authorRepository.findById(idAuthor);
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
            book.setTitle(title);
            book.setAuthor(author);
            book.setEditorial(editorial);
            book.setCopies(copies);
            
            bookRepository.save(book);
        }
        
    } 
    
    private void validate(Long isbn, String title, Integer copies, String idAuthor, String idEditorial) throws MyException {
        //Validations
        if(isbn==null) {
            throw new MyException("El campo isbn no puede ser nulo");
        }
        
        if(title==null || title.isEmpty()) {
            throw new MyException("El campo title no puede ser nulo o vacio");
        }
        
        if(copies==null || copies<0) {
            throw new MyException("El campo copies no puede ser nulo o un número menor que cero");
        }
        
        if(idAuthor==null || idAuthor.isEmpty()) {
            throw new MyException("El campo idAuthor no puede ser nulo o vacío");

        }
        
        if(idEditorial==null || idEditorial.isEmpty()) {
            throw new MyException("El campo idEditorial no puede ser nulo o vacío");
        }
    }
}
