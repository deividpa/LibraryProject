package com.dpa.LibraryProject.services;

import com.dpa.LibraryProject.entities.Editorial;
import com.dpa.LibraryProject.exceptions.MyException;
import com.dpa.LibraryProject.repositories.EditorialRepository;
import jakarta.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
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
    
    @Transactional
    public void createEditorial(String name) throws MyException {
        if(name==null || name.isEmpty()) {
            throw new MyException("El campo name no puede ser nulo o vacío");
        }
            
        Editorial editorial = new Editorial();
        editorial.setName(name);
        editorialRepository.save(editorial);
    }
    
    public List<Editorial> listEditorials() {
        List<Editorial> editorials = new ArrayList();
        editorials = editorialRepository.findAll();
        
        return editorials;
    }
    
    @Transactional
    public void updateEditorial(String id, String name) throws MyException {
        validate(id, name);
        
        Optional<Editorial> responseEditorial = editorialRepository.findById(id);
        
        if(responseEditorial.isPresent()) {
            Editorial editorial = responseEditorial.get();
            editorial.setName(name);
            
            editorialRepository.save(editorial);
        }
    }
    
    private void validate(String id, String name) throws MyException {
        //Validations
        if(id==null || id.isEmpty()) {
            throw new MyException("El campo id no puede ser nulo o vacío");
        }
        
        if(name==null || name.isEmpty()) {
            throw new MyException("El campo name no puede ser nulo o vacío");
        }
    }
}
