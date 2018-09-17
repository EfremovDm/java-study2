package ru.efremovdm.lesson1.obstacles;
import ru.efremovdm.lesson1.animals.*;

/**
 * Препятствие: вода
 */
public class Water implements Let {

    private int length;

    public Water(int length) {
        this.length = length;
    }

    @Override
    public boolean doIt(Animal animal) {
        if (animal instanceof Swimable)
            return ((Swimable) animal).swim(length);
        else
            return false;
    }

    public int getLength(){
        return length;
    }
}