package ru.efremovdm.Lesson1.Obstacles;
import ru.efremovdm.Lesson1.Animals.*;

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