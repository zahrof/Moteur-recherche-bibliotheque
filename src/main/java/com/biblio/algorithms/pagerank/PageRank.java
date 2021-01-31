package com.biblio.algorithms.pagerank;

import com.biblio.algorithms.jaccard.Jaccard;
import com.biblio.models.Book;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class PageRank {

    private final static int MAX_TURN = 30;

    private static Double[][] matrix = null;

    public static void init(){
        matrix = Jaccard.readMatrix();
    }

    public static Double[] getJaccardSum(List<Book> books, Double[][] matrix){
        System.out.println(matrix);
        Double[] js = new Double[books.size()];
        for(int i = 0; i < js.length; i++)
            js[i] = 0.0;
        for(int i = 0; i < books.size(); i++)
            for(int j = 0; j < i; j++) {
                int n = Math.max(books.get(i).getIsbn(),books.get(j).getIsbn());
                int m = Math.min(books.get(i).getIsbn(),books.get(j).getIsbn());
                if (matrix[books.get(i).getIsbn()][books.get(j).getIsbn()] > 0.49) continue;
                js[i] += matrix[books.get(i).getIsbn()][books.get(j).getIsbn()];
                js[j] += matrix[books.get(i).getIsbn()][books.get(j).getIsbn()];
            }
        return js;
    }

    public static List<Book> process(List<Book> response, Double[][] matrix){
        if (PageRank.matrix == null) init();
        matrix = PageRank.matrix;
        Double[] js = getJaccardSum(response, matrix);
        Double[] turn = new Double[response.size()];
        Double[] next_turn = new Double[response.size()];

        for(int i = 0; i < turn.length; i++) {
            turn[i] = 1.0/turn.length; next_turn[i] = 0.0;
        }

        for(int t = 0; t < MAX_TURN; t++) {
            for (int i = 0; i < response.size(); i++)
                for (int j = 0; j < i; j++) {
                    if (matrix[response.get(i).getIsbn()][response.get(j).getIsbn()] > 0.49) continue;
                    next_turn[j] += turn[i]*(1-matrix[response.get(i).getIsbn()][response.get(j).getIsbn()])/ js[i];
                    next_turn[i] += turn[j]*(1-matrix[response.get(i).getIsbn()][response.get(j).getIsbn()])/ js[j];
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
