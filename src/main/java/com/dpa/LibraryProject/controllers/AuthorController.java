package com.dpa.LibraryProject.controllers;

import com.dpa.LibraryProject.exceptions.MyException;
import com.dpa.LibraryProject.services.AuthorService;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 *
 * @author David Perez
 */
@Controller
@RequestMapping("/author")
public class AuthorController {
    
    @Autowired
    private AuthorService authorService;
    
    @GetMapping("/register")
    public String register() {
        return "author_form.html";
    }
    
    @PostMapping("/signup")
    public String signUp(@RequestParam String name) {
        try {
            authorService.createAuthor(name);
            
        } catch(MyException MyEx) {
            Logger.getLogger(AuthorController.class.getName()).log(Level.SEVERE, null, MyEx);
            return "author_form.html";
        }
        
        return "index.html";
    }
}
