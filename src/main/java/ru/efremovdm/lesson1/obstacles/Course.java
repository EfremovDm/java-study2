package ru.efremovdm.lesson1.obstacles;

import java.util.*;
import ru.efremovdm.lesson1.animals.*;

/**
 * 3. Добавить класс Course (полоса препятствий), в котором будут находиться массив
 * препятствий и метод, который будет просить команду пройти всю полосу;
 */
public class Course {

    private Let[] let = new Let[3];

    /**
     * Создаем полосу препятствий
     */
    public Course() {

        Random random = new Random();

        Track track = new Track(random.nextInt(100));
        Wall wall = new Wall(random.nextFloat() * 10);
        Water water = new Water(random.nextInt(100));

        let[0] = (Let) track;
        let[1] = (Let) wall;
        let[2] = (Let) water;
    }

    /**
     * Вывод информации о полосе препятствий
     */
    public void printInfo() {
        System.out.println("Length track: " + ((Track) let[0]).getLength());
        System.out.println("Height wall: " + ((Wall) let[1]).getHeight());
        System.out.println("Length water: " + ((Water) let[2]).getLength());
        System.out.println();
    }

    /**
     * Прохождение полосы препятствий
     *
     * @param team
     */
    public void passObstacles(Team team){
        for(Animal animal : team.getTeamAnimal()){
            for(Let l : let){
                if(!l.doIt(animal)){
                    animal.setPassing(l.doIt(animal));
                    break;
                }
                animal.setPassing(true);
            }
        }
    }
}