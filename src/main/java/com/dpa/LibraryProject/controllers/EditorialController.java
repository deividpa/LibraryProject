package com.dpa.LibraryProject.controllers;

import com.dpa.LibraryProject.exceptions.MyException;
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
@RequestMapping("/editorial")
public class EditorialController {
    @Autowired
    private EditorialService editorialService;
    
    @GetMapping("/create") 
    public String create() {
       return "Editorial/create.html";
    }
    
    @PostMapping("/creating") 
    public String create(@RequestParam(required=false) String name, ModelMap model) {
        try {
            editorialService.createEditorial(name);
            model.put("success", "Editorial created successfully!");
        } catch(MyException MyEx) {
            model.put("error", "There was an error creating the new Editorial!");
            return "Editorial/create.html";
        }
        return "Editorial/index.html";
    }
}
