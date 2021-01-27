package com.biblio.algorithms;

import com.biblio.models.Book;

public class KMP {

    // renvoi vrai si keyword est présent dans book
    public static boolean kmp(Book book, String keyword){
        String text = book.text;
        boolean present = false;
        // m désigne la position dans le text text à laquelle
        // la chaîne keyword est en cours de verif
        int keywordLength = keyword.length();
        int textLenght = text.length();

        int carryover[] = new int[keywordLength];
        int j =0;

        findCarryOver(keyword, keywordLength, carryover);

        int i = 0;
        while (i < textLenght ) {
            if (keyword.charAt(j) == text.charAt(i)){
                j++;
                i++;
            }
            if (j == keywordLength ){
                System.out.println("Le mot commence à l'indice " + ( i - j));
                present = true;
                j = carryover[j - 1];
            }
            else if ( i < textLenght && keyword.charAt(j) != text.charAt(i)) {
                if (j != 0) j = carryover[j - 1];
                else i = i+1;

            }
        }

        return present;
    }

    private static void findCarryOver(String keyword, int keyWordLength, int[] carryOver) {
        int len = 0;
        // puisque une lettre peux pas être suffixe et préfixe on initialise tout à 0
        int i = 1;
        carryOver[0] = 0;

        while ( i < keyWordLength ) {
            if (keyword.charAt(i) == keyword.charAt(len)){
                len++;
                carryOver[i] = len ;
                i++;
            }
            else{
                if(len != 0) len = carryOver[len - 1];
                else{
                    carryOver[i] = len ;
                    i++;
                }
            }
        }
    }
}
