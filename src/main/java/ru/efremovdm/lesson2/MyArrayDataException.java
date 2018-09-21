package ru.efremovdm.lesson2;

public class MyArrayDataException extends Exception{

    private String str;

    MyArrayDataException(String ex) {
        str = ex;
    }

    public String toString(){
        return ("MyArrayDataException: " + str) ;
    }
}