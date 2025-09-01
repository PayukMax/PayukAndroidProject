package com.example.payukproject.Utils;

import java.util.Random;

public class Crpt {

    public static String Encrypte(String str) {
        StringBuilder newStr = new StringBuilder("");
        Random rnd = new Random();
//        String newStr="";
        for (int i = 0; i < 150; i++) {
            newStr = newStr.append((char) (rnd.nextInt(124) + 2));
        }
        int[] mass = new int[str.length() + 1]; //  в массиве храним позиции по которым поставим наши символы
        boolean flag;
        for (int i = 0; i < mass.length - 1; i++) {
            flag = true;
            while (flag) {
                int pos = rnd.nextInt(100) + 16; // пароль не более 15 символов!!!
                boolean found_pos = true;
                for (int j = 0; j < i; j++) {
                    if (mass[j] == pos) {
                        found_pos = false;
                        break;
                    }
                }
                if (found_pos) {
                    mass[i] = pos;
                    flag = false;
                }
            }
        }
        for (int i = 0; i < mass.length - 1; i++) {
            newStr.setCharAt(i, (char) mass[i]);
            newStr.setCharAt(mass[i], str.charAt(i));
        }
        newStr.setCharAt(mass.length - 1, (char) mass[mass.length - 1]);
        return newStr.toString();
    }

    public static String Decrypt (String str) {
        String retStr= "";
        boolean flag = true;
        int xxx;
        int index = 0;
        while (flag){
            xxx = str.charAt(index);
            if(xxx==0 || index>=150) flag=false; else {
                retStr=retStr+str.charAt(xxx);
                index++;
            }
        }
        return retStr;
    }
}
