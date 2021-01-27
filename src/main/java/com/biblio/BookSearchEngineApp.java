package com.biblio;

import com.biblio.models.Book;
import com.biblio.repositories.db.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.ArrayList;

@SpringBootApplication
public class BookSearchEngineApp {

    @Autowired
    private BookRepository BookRepository;

    public static void main(String[] args) {
        SpringApplication.run(BookSearchEngineApp.class, args);
    }

    @Bean
    public CommandLineRunner demo(BookRepository repository) {
        return (args) -> {

            Book b1 = new Book("IBSN1", "title1", "text1");
            Book b2 = new Book("IBSN2", "title2", "La petite fille de la ferme préfère chanter");
            Book b3 = new Book("IBSN1", "title3", "Un chien aime pas porter de collier");

            repository.save(b1); repository.save(b2); repository.save(b3);
            for(Book book : repository.findAll())
                System.out.println(book);
            System.out.println("----END----");
        };
    }



}
