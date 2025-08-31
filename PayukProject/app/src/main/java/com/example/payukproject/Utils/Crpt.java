package com.example.payukproject.Utils;

import java.util.Random;

public class Crpt {

    public static String Encrypte(String str) {
        StringBuilder newStr = new StringBuilder("");
        Random rnd = new Random();
//        String newStr="";
        for (int i = 0; i < 100; i++) {
            newStr = newStr.append((char)rnd.nextInt(200));
        }
        int [] mass = new int [str.length()+1]; //  в массиве храним позиции по которым поставим наши символы
        for (int i = 0; i < mass.length; i++) {

            while (true){
                int pos = 25+rnd.nextInt(75); // пароль не более 25 символов!!!
                for (int j = 0; j <= i; j++) {
                    if (mass[j]==pos)
                }
            }


        }
        for (int i = 0; i < str.length(); i++) {
            // вычисляем позицию символа в шифРОВАННОЙ СТРОКЕ
            int pos =
            newStr.setCharAt(i,);
        }

        return newStr.toString();
    }
}
