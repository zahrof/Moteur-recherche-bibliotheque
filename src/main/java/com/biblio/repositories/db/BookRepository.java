package com.biblio.repositories.db;

import com.biblio.models.Book;
import org.springframework.data.elasticsearch.annotations.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookRepository extends CrudRepository<Book, String>, BookRepositoryCustom<Book, String> {

}
