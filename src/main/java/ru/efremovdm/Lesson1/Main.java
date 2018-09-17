package ru.efremovdm.Lesson1;

import ru.efremovdm.Lesson1.animals.Team;
import ru.efremovdm.Lesson1.obstacles.Course;

/**
 * 1. Разобраться с имеющимся кодом;
 *
 * 2. Добавить класс Team, который будет содержать название команды, массив из четырех участников (в конструкторе можно сразу указыватьвсех участников ),
 * метод для вывода информации о членах команды, прошедших дистанцию, метод вывода информации обо всех членах команды.
 *
 * 3. Добавить класс Course (полоса препятствий), в котором будут находиться массив препятствий и метод, который будет просить команду пройти всю полосу;
 */
public class Main
{
    public static void main( String[] args )
    {
        Team team1 = new Team("Capitalism");
        team1.printInfo();

        Team team2 = new Team("Communism");
        team2.printInfo();

        Course course = new Course();
        course.printInfo();
        course.passObstacles(team1);
        course.passObstacles(team2);

        team1.passedTheDistance();
        team2.passedTheDistance();
    }
}
