package com.dpa.LibraryProject.repositories;

import com.dpa.LibraryProject.entities.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 *
 * @author David Perez
 */
    
@Repository
public interface BookRepository extends JpaRepository<Book, Long> {
    @Query("SELECT b FROM Book b WHERE b.title = :title")
    public Book searchByTitle(@Param("title") String title);
    
    @Query("SELECT b FROM Book b WHERE b.author.name = :name")
    public List<Book> searchByAuthorName(@Param("name")  String name);
}
