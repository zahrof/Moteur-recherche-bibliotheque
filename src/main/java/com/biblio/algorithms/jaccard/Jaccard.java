package com.biblio.algorithms.jaccard;

import lombok.Getter;

import java.io.*;
import java.lang.reflect.Array;
import java.util.*;
import java.util.stream.Collectors;

@Getter
public class Jaccard {

    private static final String SAVE_PATH = "books/matrix.txt";
    private static final String BOOK_FOLDER_PATH = "books/";
    private static final int HOW_MANY = 200;
    private Double[][] jaccard = null;

    public Jaccard(){
        File file = new File(SAVE_PATH);
        if (!file.exists())
            jaccard = readMatrix();
        else {
            jaccard = getMatrix(BOOK_FOLDER_PATH, HOW_MANY);
            save(jaccard);
        }
    }

    public static Map<String, Integer> frqOfWords(String filePath){
        Map<String, Integer> frq = new HashMap<>();
        BufferedReader reader;
        try {
            reader = new BufferedReader(new FileReader(filePath));
            String line = reader.readLine();
            while (line != null){
                String[] words = line.replace("\n", "").split(" ");
                for(String word : words){
                    if (word == "" || word == null) continue;
                    frq.put(word, frq.getOrDefault(word, 0) + 1);
                }
                line = reader.readLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return frq;
    }

    public static Double[][] getMatrix(String folderPath, int n){
        List<Map<String, Integer>> fow = new ArrayList<>();
        for(int i = 0; i < n; i++)
            fow.add(frqOfWords(folderPath + Integer.toString(i) + ".txt"));
        Double[][] matrix = (Double[][]) Array.newInstance(Double.class, n, n);
        for(int i = 0; i < n; i++) {
            for (int j = 0; j <= i; j++)
                matrix[i][j] = matrix[j][i] = jaccardDistance(fow.get(i), fow.get(j));
        }
        return matrix;
    }

    public static Double jaccardDistance(Map<String, Integer> a, Map<String, Integer> b){
        int num = 0;
        int den = 0;
        for(String key : a.keySet()) {
            den += Math.max(a.get(key), b.getOrDefault(key, 0));
            num += Math.abs(a.get(key) - b.getOrDefault(key,0));
        }

        for(String key : b.keySet())
            if (!a.keySet().contains(key))
                den += b.get(key);
        return (1.0*num)/Math.max(den,1);
    }

    public static String toString(Double[][] matrix){
        String s = "";
        for(Double[] l : matrix) {
            for (Double d : l)
                s += d + " ";
            s += "\n";
        }
        return s;
    }

    public static Double[][] save(Double[][] matrix){
        BufferedWriter writer;
        try {
            File file = new File(SAVE_PATH);
            if (!file.exists()) file.createNewFile();
            writer = new BufferedWriter(new FileWriter(file.getAbsoluteFile()));
            writer.write(toString(matrix));
            writer.close();
        } catch (IOException e){
            e.printStackTrace();
        } finally {
            return matrix;
        }
    }

    public static Double[][] readMatrix(){
        BufferedReader reader;
        List<List<Double>> _matrix = new ArrayList<>();
        try {
            reader = new BufferedReader(new FileReader(SAVE_PATH));
            String line = reader.readLine();
            while (line != null) {
                _matrix.add(Arrays.stream(line.replace("\n", "").split(" ")).
                        map(e -> Double.valueOf(e)).collect(Collectors.toList()));
                line = reader.readLine();
            }
            Double[][] matrix = (Double[][]) Array.newInstance(Double.class, _matrix.size(), _matrix.size());
            for(int i = 0; i < _matrix.size(); i++)
                for(int j = 0; j <= i; j++)
                    matrix[i][j] = matrix[j][i] =_matrix.get(i).get(j);
            return matrix;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

}
