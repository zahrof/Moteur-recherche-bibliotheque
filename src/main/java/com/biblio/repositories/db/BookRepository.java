package com.biblio.repositories.db;

import com.biblio.models.Book;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface BookRepository extends CrudRepository<Book, String>{

    Book findByisbn(int isbn);
}
