package com.biblio.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
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

    @JsonIgnore
    public final String text;

    @JsonIgnore
    public final ArrayList<ArrayList<String>> content;

    public String toString(){
        return title + " [" + ibsn + "]";
    }
}
