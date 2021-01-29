package com.biblio.models;

import lombok.AllArgsConstructor;
import lombok.Getter;

import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.annotation.Id;

import java.util.ArrayList;

@AllArgsConstructor
@Getter
@Document(indexName = "ibsn", type = "book")
public class Book {

    @Id
    public final String ibsn;

    public final String title;

    // TO DO
    public final String text;

    public final ArrayList<ArrayList<String>> content;

    public String toString(){
        return title + " [" + ibsn + "]";
    }
}
