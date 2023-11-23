package com.dpa.LibraryProject.controllers;

import com.dpa.LibraryProject.entities.Author;
import com.dpa.LibraryProject.exceptions.MyException;
import com.dpa.LibraryProject.services.AuthorService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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
    
    @PostMapping("/create")
    public String signUp(@RequestParam String name, ModelMap model) {
        try {
            authorService.createAuthor(name);
            model.put("success", "Author was created successfully.");
        } catch(MyException MyEx) {
            //Logger.getLogger(AuthorController.class.getName()).log(Level.SEVERE, null, MyEx);
            model.put("error", "There was an error creating the Author.");
            return "author_form.html";
        }
        return "Author/index.html";
    }
    
    @GetMapping("/index")
    public String list(ModelMap model) {
        List<Author> authors = authorService.listAuthors();
        model.addAttribute("authors", authors);
        
        return "Author/index.html";
    }
    
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable String id, ModelMap model) {
        Author author = authorService.getOne(id);
        model.put("author", author);
        
        return "Author/edit.html";
    }
    
    @PostMapping("/edit/{id}")
    public String edit(@PathVariable String id, String name, ModelMap model) {
        try {
            List<Author> authors = authorService.listAuthors();
            authorService.updateAuthor(id, name);      
            
            model.put("success", "Author was edited successfully!");  
            model.addAttribute("authors", authors);
        } catch(MyException MyEx) {
            model.put("error", MyEx.getMessage());
            return "Author/edit.html";
        }
        //return "redirect:../index";
        return "Author/index.html";
    }
}
