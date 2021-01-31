package com.biblio.algorithms.pagerank;

import com.biblio.models.Book;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class PageRank {

    private final static int MAX_TURN = 10;

    public static Double[] getJaccardSum(List<Book> books, Double[][] matrix){
        Double[] js = new Double[books.size()];
        for(int i = 0; i < books.size(); i++)
            for(int j = 0; j < i; j++) {
                js[i] += matrix[books.get(i).getIsbn()][books.get(j).getIsbn()];
                js[j] += matrix[books.get(i).getIsbn()][books.get(j).getIsbn()];
            }
        return js;
    }

    public static List<Book> process(List<Book> response, Double[][] matrix){
        Double[] js = getJaccardSum(response, matrix);
        Double[] turn = new Double[response.size()];
        Double[] next_turn = new Double[response.size()];

        for(int i = 0; i < turn.length; i++) {
            turn[i] = 1.0/turn.length; next_turn[i] = 0.0;
        }

        for(int t = 0; t < MAX_TURN; t++) {
            for (int i = 0; i < response.size(); i++)
                for (int j = 0; j < i; j++) {
                    next_turn[j] += matrix[response.get(i).getIsbn()][response.get(j).getIsbn()] / js[i];
                    next_turn[j] += matrix[response.get(i).getIsbn()][response.get(j).getIsbn()] / js[j];
                }
            for(int i = 0; i < turn.length; i++) {
                turn[i] = next_turn[i]; next_turn[i] = 0.0;
            }
        }

        Double[][] tmp = new Double[turn.length][2];
        for(int i = 0; i < turn.length; i++) {
            tmp[i][0] = turn[i];
            tmp[i][1] = i * 1.0;
        }
        Arrays.sort(tmp, (a, b) -> Double.compare(a[0], b[0]));
        List<Book> sorted = new ArrayList<>();
        for(Double[] e : tmp)
            sorted.add(response.get((int)Math.round(e[1])));
        return  sorted;


    }
}
