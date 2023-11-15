package com.dpa.LibraryProject.repositories;

import com.dpa.LibraryProject.entities.Author;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author David Perez
 */
@Repository
public interface AuthorRepository extends JpaRepository<Author, String> {
    
}
