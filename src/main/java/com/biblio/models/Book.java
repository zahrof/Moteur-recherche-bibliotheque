package com.biblio.models;

import lombok.AllArgsConstructor;
import lombok.Getter;

import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.annotation.Id;

@AllArgsConstructor
@Document(indexName = "ibsn", type = "book")
public class Book {

    @Id
    private String ibsn;

    private String title;

    private String text;

    public String toString(){
        return title + " [" + ibsn + "]";
    }
}
