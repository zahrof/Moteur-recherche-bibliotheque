package com.biblio.repositories.db;

import com.biblio.models.Book;

import javax.persistence.criteria.Expression;
import java.util.List;

public interface BookRepositoryCustom<Book, String> {

    List<Book> findByKeyword(String Keyword);
}
