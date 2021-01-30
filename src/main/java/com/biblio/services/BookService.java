package com.biblio.services;

import com.biblio.models.Book;
import com.biblio.repositories.db.BookRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.data.elasticsearch.core.ElasticsearchRestTemplate;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.core.mapping.IndexCoordinates;
import org.springframework.data.elasticsearch.core.query.*;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class BookService {

    private static final String BOOK_INDEX = "books";

    private ElasticsearchOperations elasticsearchOperations;

    public List<String> createBookIndexBulk(List<Book> books) {
        List<IndexQuery> queries = books.stream()
                .map(book ->
                        new IndexQueryBuilder()
                .withId(book.getIbsn().toString())
                .withObject(book).build())
                .collect(Collectors.toList());

        return elasticsearchOperations.bulkIndex(queries, IndexCoordinates.of(BOOK_INDEX));
    }

    public String createBookIndex(Book book){
        IndexQuery indexQuery = new IndexQueryBuilder()
                .withId(book.getIbsn().toString())
                .withObject(book).build();
        return elasticsearchOperations.index(indexQuery, IndexCoordinates.of(BOOK_INDEX));
    }

    public void findByKeyword(String keyword){
        Criteria criteria = new Criteria("content").
    }

}
