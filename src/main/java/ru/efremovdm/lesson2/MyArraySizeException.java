package ru.efremovdm.lesson2;

public class MyArraySizeException extends Exception{

    private String str;

    MyArraySizeException(String ex) {
        str = ex;
    }

    public String toString(){
        return ("MyArraySizeException: " + str) ;
    }
}