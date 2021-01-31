package com.biblio.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;

import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.annotation.Id;

import java.util.ArrayList;

@AllArgsConstructor
@Getter
@Document(indexName = "isbn", type = "book")
public class Book {

    @Id
    public final int isbn;

    public final String title;

    @JsonIgnore
    public final ArrayList<ArrayList<String>> content;

    public String toString(){
        return title + " [" + isbn + "]";
    }
}
