package com.biblio.controller;

import com.biblio.algorithms.KMP;
import com.biblio.models.Book;
import com.biblio.repositories.db.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;


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



}
