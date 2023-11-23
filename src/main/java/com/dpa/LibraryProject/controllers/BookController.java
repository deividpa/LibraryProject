package com.dpa.LibraryProject.controllers;

import com.dpa.LibraryProject.entities.Author;
import com.dpa.LibraryProject.entities.Book;
import com.dpa.LibraryProject.entities.Editorial;
import com.dpa.LibraryProject.exceptions.MyException;
import com.dpa.LibraryProject.services.AuthorService;
import com.dpa.LibraryProject.services.BookService;
import com.dpa.LibraryProject.services.EditorialService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 *
 * @author David Perez
 */
@Controller
@RequestMapping("/book")
public class BookController {
    @Autowired
    private BookService bookService;
    @Autowired
    private AuthorService authorService;
    @Autowired
    private EditorialService editorialService;
    
    @GetMapping("/create")
    public String crear(ModelMap model) {
        List<Author> authors = authorService.listAuthors();
        List<Editorial> editorials = editorialService.listEditorials();
        
        model.addAttribute("authors", authors);
        model.addAttribute("editorials", editorials);
        return "create_book.html";
    }
    
    @PostMapping("/creating")
    public String creating(@RequestParam(required=false) Long isbn, 
            @RequestParam(required=false) Integer copies, @RequestParam(required=false) String title,
                @RequestParam String idAuthor, @RequestParam String idEditorial, ModelMap model) {
        
        try {
            bookService.createBook(isbn, title, copies, idAuthor, idEditorial);
            model.put("success", "The book was created successfully!");
        } catch(MyException MyEx) {
            List<Author> authors = authorService.listAuthors();
            List<Editorial> editorials = editorialService.listEditorials();
            List<Book> books = bookService.listBooks();
            
            model.addAttribute("books", books);
            model.addAttribute("authors", authors);
            model.addAttribute("editorials", editorials);
            
            model.put("error", MyEx.getMessage());
            return "create_book.html";
        }

        return "Book/index.html";
    }
    
    @GetMapping("/index")
    public String list(ModelMap model) {
        List<Book> books = bookService.listBooks();
        model.addAttribute("books", books);
        
        return "Book/index.html";
    }
}
