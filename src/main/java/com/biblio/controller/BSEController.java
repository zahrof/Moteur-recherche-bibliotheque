package com.biblio.controller;

import com.biblio.algorithms.KMP;
import com.biblio.egrepClasses.RegEx;
import com.biblio.egrepClasses.ShortBook;
import com.biblio.models.Book;
import com.biblio.repositories.db.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

import static com.biblio.egrepClasses.utilitaryClass.regExIsPresent;


@RestController
@RequestMapping
public class BSEController {

    @Autowired
    private BookRepository repository;

    @Autowired
    private HttpServletRequest request;

    //@RequestMapping(value = "/query")
    @GetMapping(value = "/searchByKeyword", produces = {"application/json"})
    public List<Book> searchByKeyword(@RequestParam String keyword){
        System.out.println("----- QUERY ----- "+ keyword );

        List<Book> result = new ArrayList<>();
        for(Book b : repository.findAll())
            if(KMP.kmp(b, keyword))
                result.add(b);
        return result;
    }

    @GetMapping(value = "/searchByRegEx", produces = {"application/json"})
    public List<Book> searchByRegEx(@RequestParam String regEx){
        // retrieving the regEx
        System.out.println("----- QUERY ----- "+ regEx);
        RegEx re = new RegEx();
        re.regEx = regEx;

        // retrieving from elasticsearch each book and checking if the reg ex is present
        List<Book> result = new ArrayList<>();
        for(Book b : repository.findAll()) {
            ShortBook sb = new ShortBook(b.getBook());
            if (regExIsPresent(re, sb)) {
                System.out.println("Is present");
                result.add(b);
            }
        }

        return result;
    }



}
