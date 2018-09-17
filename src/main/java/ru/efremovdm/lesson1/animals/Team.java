package ru.efremovdm.lesson1.animals;

import java.util.Random;

/**
 * 2. Добавить класс Team, который будет содержать название команды, массив из четырех участников
 * (в конструкторе можно сразу указыватьвсех участников ), метод для вывода информации о членах
 * команды, прошедших дистанцию, метод вывода информации обо всех членах команды.
 */
public class Team
{
    private String nameTeam = "unknown";

    private Animal[] teamAnimal = new Animal[4];

    private Animal[] zoo = {
        new Cat("Barsik"),
        new Dog("Puppy"),
        new Hedgehog("NufNuf")
    };

    /**
     * Формируем команду из 3-х случайно набранных сущностей
     *
     * @param nameTeam - название команды
     */
    public Team(String nameTeam) {
        this.nameTeam = nameTeam;
        Random random = new Random();
        for(int i = 0; i < 4; i++){
            int ran = random.nextInt(3);
            teamAnimal[i] = zoo[ran];
        }
    }

    public Animal[] getTeamAnimal() {
        return teamAnimal;
    }

    /**
     * Вывод информации о членах команды
     */
    public void printInfo() {
        System.out.println("Team: " + nameTeam);
        for(Animal a : teamAnimal) {
            System.out.println(a);
            System.out.println("Run limit: " + a.run_limit);
            if (a instanceof Swimable)
                System.out.println("Swim limit: " + ((Swimable) a).getSwimLimit());
            if (a instanceof Jumpable)
                System.out.println("Jump limit: " + ((Jumpable) a).getJumpLimit());
        }
        System.out.println();
    }

    /**
     *  Вывод информации кто прошел дистанцию
     */
    public void passedTheDistance(){
        System.out.println(nameTeam);
        for (Animal a : teamAnimal){
            if(a.passing){
                System.out.println(a + " successfully passed the distance.");
            } else {
                System.out.println(a + " did not pass the distance.");
            }
        }
        System.out.println();
    }
}
