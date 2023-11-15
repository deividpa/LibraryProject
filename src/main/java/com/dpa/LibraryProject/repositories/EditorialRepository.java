package com.dpa.LibraryProject.repositories;

import com.dpa.LibraryProject.entities.Editorial;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author David Perez
 */
@Repository
public interface EditorialRepository extends JpaRepository<Editorial, String> {
    
}
