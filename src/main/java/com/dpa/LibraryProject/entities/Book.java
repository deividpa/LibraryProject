package com.dpa.LibraryProject.entities;

import java.util.Date;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;

/**
 *
 * @author David Perez
 */
@Entity
public class Book {
    @Id
    private Long isbn;
    private String title;
    private Integer copies;
    
    @Temporal(TemporalType.DATE)
    private Date regDate;
}
