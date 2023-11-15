package com.dpa.LibraryProject.services;

import com.dpa.LibraryProject.entities.Editorial;
import com.dpa.LibraryProject.repositories.EditorialRepository;
import jakarta.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author David Perez
 */
@Service
public class EditorialService {
    
    @Autowired
    private EditorialRepository editorialRepository;
    
    Editorial editorial = new Editorial();
    
    @Transactional
    private void createEditorial(String name) {
        editorial.setName(name);
        editorialRepository.save(editorial);
    }
    
    private List<Editorial> listEditorials() {
        List<Editorial> editorials = new ArrayList();
        editorials = editorialRepository.findAll();
        
        return editorials;
    }
}
