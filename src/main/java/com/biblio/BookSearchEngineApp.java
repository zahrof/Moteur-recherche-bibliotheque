package com.biblio;

import com.biblio.algorithms.egrep.ShortBook;
import com.biblio.algorithms.jaccard.Jaccard;
import com.biblio.algorithms.pagerank.PageRank;
import com.biblio.controller.BSEController;
import com.biblio.models.Book;
import com.biblio.repositories.db.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.context.annotation.Bean;

import java.util.ArrayList;
import java.util.List;

@SpringBootApplication(exclude = SecurityAutoConfiguration.class)
public class BookSearchEngineApp {

    @Autowired
    private BookRepository BookRepository;


    public static void main(String[] args) {
        SpringApplication.run(BookSearchEngineApp.class, args);
    }

    @Bean
    public CommandLineRunner demo(BookRepository repository) {

        return (args) -> {
            //  repository.deleteAll();
            //for (int i=0; i <500; i++)
             //   repository.save(new Book(i, i+".txt",(new ShortBook("books/" + i + ".txt")).book ));

            System.out.println("counter "+ repository.count());
            //Jaccard.save(Jaccard.getMatrix("books/", 500));
            PageRank.init();
            System.out.println("---- READY ----");

        };
    }



}
