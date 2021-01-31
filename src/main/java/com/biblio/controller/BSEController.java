package com.biblio.controller;

import com.biblio.algorithms.jaccard.Jaccard;
import com.biblio.algorithms.kmp.KMP;
import com.biblio.algorithms.egrep.MinimalizedAutomaton;
import com.biblio.algorithms.egrep.RegEx;
import com.biblio.algorithms.egrep.ShortBook;
import com.biblio.algorithms.pagerank.PageRank;
import com.biblio.models.Book;
import com.biblio.repositories.db.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

import static com.biblio.algorithms.egrep.utilitaryClass.regExIsPresent;


@RestController
@RequestMapping
public class BSEController {

    @Autowired
    private BookRepository repository;

    @Autowired
    private HttpServletRequest request;

    @Autowired
    private static Double[][] matrix = null;


    //@RequestMapping(value = "/query")
    @GetMapping(value = "/searchByKeyword", produces = {"application/json"})
    public List<Book> searchByKeyword(@RequestParam String keyword){
        System.out.println("----- QUERY ----- "+ keyword );

        List<Book> result = new ArrayList<>();
        for(Book b : repository.findAll())
            if(KMP.kmp(b, keyword))
                result.add(b);

        System.out.println(result.size());
        List<Book> sorted = PageRank.process(result, Jaccard.readMatrix());
        System.out.println(sorted.size());
        System.out.println("----- ANSWER ----- "+ keyword );

        return sorted;
    }

    @GetMapping(value = "/searchByRegEx", produces = {"application/json"})
    public List<Book> searchByRegEx(@RequestParam String regEx){
        // retrieving the regEx
        System.out.println("----- QUERY ----- "+ regEx);
        RegEx re = new RegEx();
        re.regEx = regEx;

       // retrieving from elasticsearch each book and checking if the reg ex is present
        List<Book> result = new ArrayList<>();

        MinimalizedAutomaton ms = new MinimalizedAutomaton(re);
        for(Book b : repository.findAll()) {
            ShortBook sb = new ShortBook(b.getContent());
            if (regExIsPresent(sb,ms.clone())){
                result.add(b);

            }

        }
        System.out.println(result.size());
        List<Book> sorted = PageRank.process(result, matrix);
        System.out.println(sorted.size());
        System.out.println("----- ANSWER ----- "+ regEx );

        return sorted;
    }



}
