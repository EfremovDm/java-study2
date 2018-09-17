package ru.efremovdm.lesson2;

public class MyArraySizeException extends Exception{

    private String str1;

    MyArraySizeException(String str2) {
        str1 = str2;
    }

    public String toString(){
        return ("MyArraySizeException: " + str1) ;
    }
}