package com.biblio;

import com.biblio.algorithms.egrep.ShortBook;
import com.biblio.algorithms.jaccard.Jaccard;
import com.biblio.models.Book;
import com.biblio.repositories.db.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.ArrayList;
import java.util.List;

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
          //  repository.deleteAll();
           /* ArrayList<String> page1 = new ArrayList<>();
            page1.add("Je pense qu'il vaut mieux vous dire la vérité docteur. Sargon");
            page1.add("Les enfants savent se montrer très philosophes");

            ArrayList<String> page2 = new ArrayList<>();
            page2.add("ça fait donc trois premières en cinq minutes ");
            page2.add("Non, Wendy. ça ne pouvait pas être un accident.");

            ArrayList<String> page3 = new ArrayList<>();
            page3.add("Alors la femme se mit sur son séant");
            page3.add("Il ouvrit les yeux et s'essuya la bouche avec son mouchoir.");

            ArrayList<String> page4 = new ArrayList<>();
            page4.add("Alors la femme se mit sur son séant");
            page4.add("C'est une excellent idée");

            ArrayList<String> page5 = new ArrayList<>();
            page5.add("A deux heures et demie.Sargon");
            page5.add("Le jour tombait");

            ArrayList<String> page6 = new ArrayList<>();
            page6.add("Elle sourit");
            page6.add("Il ferma les yeux, serra les poings et se concentra si fort que ses épaules se contractèrent");

            ArrayList<ArrayList<String>> livre1 = new ArrayList<>();
            livre1.add(page1);
            livre1.add(page2);

            ArrayList<ArrayList<String>> livre2 = new ArrayList<>();
            livre2.add(page3);
            livre2.add(page4);

            ArrayList<ArrayList<String>> livre3 = new ArrayList<>();
            livre3.add(page5);
            livre3.add(page6);



            Book b1 = new Book("isbn1", "title1", "text1",livre1);
            Book b2 = new Book("isbn2", "title2", "La petite fille de la ferme préfère chanter",livre2);
            Book b3 = new Book("isbn3", "title3", "Un chien aime pas porter de collier",livre3);

            repository.save(b1); repository.save(b2); repository.save(b3);*/
         /*   for (int i=0; i <200; i++)
                repository.save(new Book(i, i+".txt",(new ShortBook("books/" + i + ".txt")).book ));*/


                System.out.println("counter "+ repository.count());
            Jaccard.save(Jaccard.getMatrix("books/", 500));
            System.out.println("----END----");

        };
    }



}
