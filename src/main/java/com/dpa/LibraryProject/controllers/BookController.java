package com.dpa.LibraryProject.controllers;

import com.dpa.LibraryProject.exceptions.MyException;
import com.dpa.LibraryProject.services.AuthorService;
import com.dpa.LibraryProject.services.BookService;
import com.dpa.LibraryProject.services.EditorialService;
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
    public String crear() {
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
            model.put("error", MyEx.getMessage());
            return "create_book.html";
        }

        return "index.html";
    }
}
