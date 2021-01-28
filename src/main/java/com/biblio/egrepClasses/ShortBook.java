package com.biblio.egrepClasses;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class ShortBook {

    public ShortBook(ArrayList<ArrayList<String>> book) {
        this.book = book;
    }

    public ArrayList<ArrayList<String>> book;

    public ShortBook(String fileName){
        BufferedReader reader;
        book = new ArrayList<>();
        book.add(new ArrayList<>());
        int current_page = 0;
        int current_line = 0;
        try {
            reader = new BufferedReader(new FileReader(fileName));
            String line = reader.readLine();
            while (line != null) {
                line = line.replace("\n","");
                if (line.length() != 0) book.get(current_page).add(line);
                current_line++;
                if (current_line == 137){
                    current_line = 0; current_page++;
                    book.add(new ArrayList<>());
                }
                line = reader.readLine();
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String toString(){
        String str = "";
        for(ArrayList<String> page : book)
            for(String line : page) {
                str = str + line + "\n";
            }
        return str;
    }

    public int getSize(){
        return book.size();
    }

    public int getSize(int index){
        return book.get(index).size();
    }

    public ArrayList<String> getPage(int pageIndex){
        return book.get(pageIndex);
    }

    public String getLine(int pageIndex, int lineIndex){
        return book.get(pageIndex).get(lineIndex);
    }

    public char getCharacter(int pageIndex, int lineIndex, int charIndex){
        return this.getLine(pageIndex, lineIndex).charAt(charIndex);
    }


}


