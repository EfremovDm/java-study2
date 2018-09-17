package ru.efremovdm.lesson2;

public class MyArrayDataException extends Exception{

    private String str1;

    MyArrayDataException(String str2) {
        str1 = str2;
    }

    public String toString(){
        return ("MyArrayDataException: " + str1) ;
    }
}