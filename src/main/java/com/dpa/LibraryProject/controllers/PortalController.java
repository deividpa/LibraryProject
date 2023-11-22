package com.dpa.LibraryProject.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *
 * @author David Perez
 */
@Controller
@RequestMapping("/")
public class PortalController {
    
    @GetMapping("/")
    public String Index() {
        return "index.html";
    }
}
