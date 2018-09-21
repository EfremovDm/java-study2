package ru.efremovdm.lesson2;


/**
 * 1. Напишите метод, на вход которого подается двумерный строковый массив размером 4х4, при
 * подаче массива другого размера необходимо бросить исключение MyArraySizeException.
 *
 * 2. Далее метод должен пройтись по всем элементам массива, преобразовать в int, и просуммировать.
 * Если в каком-то элементе массива преобразование не удалось (например, в ячейке лежит символ или
 * текст вместо числа), должно быть брошено исключение MyArrayDataException – с детализацией,
 * в какой именно ячейке лежат неверные данные.
 *
 * 3. В методе main() вызвать полученный метод, обработать возможные исключения
 * MySizeArrayException и MyArrayDataException и вывести результат расчета.
 */
public class Application
{
    public static void main( String[] args )
    {
        try {

            String[][] ar = {
                {"123", "52345", "4", "123"},
                {"45", "4", "6", "254"},
                {"123", "0", "3", "654"},
                {"9", "4", "1", "5"}
            };

            int sum = getSum(ar);
            System.out.println("Result: " + sum);
        }
        catch (MyArraySizeException ex) {
            System.out.println(ex.toString());
        }
        catch (MyArrayDataException ex) {
            System.out.println(ex.toString());
        }
    }

    /**
     * Суммирование значений
     *
     * @param ar
     * @return
     * @throws MyArraySizeException
     * @throws MyArrayDataException
     */
    private static int getSum(String[][] ar) throws MyArraySizeException, MyArrayDataException {

        // проверка размерности
        int rows = ar.length;
        if (4 != rows) {
            throw new MyArraySizeException("not 4x4 size!");
        }
        for (String[] items: ar) {
            if (4 != items.length) {
                throw new MyArraySizeException("not 4x4 size!");
            }
        }

        // суммирование
        int sum = 0;
        for (int i = 0; i < ar.length; i++) {
            for (int j = 0; j < ar[i].length; j++) {
                try {
                    sum += new Integer(ar[i][j]);
                }
                catch (Exception ex) {
                    throw new MyArrayDataException("line [" + i + "][" + j + "]");
                }
            }
        }

        return sum;
    }
}
