package ru.efremovdm.lesson1.obstacles;
import ru.efremovdm.lesson1.animals.*;

/**
 * Препятствие: трек
 */
public class Track implements Let {

    private int length;

    Track(int length) {
        this.length = length;
    }

    @Override
    public boolean doIt(Animal animal) {
        return animal.run(length);
    }

    public int getLength(){
        return length;
    }
}