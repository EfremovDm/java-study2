package ru.efremovdm.lesson5;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 1. Необходимо написать два метода, которые делают следующее:
 *
 1) Создают одномерный длинный массив, например:
 static final int size = 10000000;
 static final int h = size / 2;
 float[] arr = new float[size];

 2) Заполняют этот массив единицами;

 3) Засекают время выполнения: long a = System.currentTimeMillis();

 4) Проходят по всему массиву и для каждой ячейки считают новое значение по формуле:
 arr[i] = (float)(arr[i] * Math.sin(0.2f + i / 5) * Math.cos(0.2f + i / 5) * Math.cos(0.4f + i / 2));

 5) Проверяется время окончания метода System.currentTimeMillis();

 6) В консоль выводится время работы: System.out.println(System.currentTimeMillis() - a);

 Отличие первого метода от второго:
 Первый просто бежит по массиву и вычисляет значения.
 Второй разбивает массив на два массива, в двух потоках высчитывает новые значения и потом склеивает эти массивы обратно в один.

 Пример деления одного массива на два:
 System.arraycopy(arr, 0, a1, 0, h);
 System.arraycopy(arr, h, a2, 0, h);

 Пример обратной склейки:
 System.arraycopy(a1, 0, arr, 0, h);
 System.arraycopy(a2, 0, arr, h, h);

 Примечание:
 System.arraycopy() копирует данные из одного массива в другой:
 System.arraycopy(массив-источник, откуда начинаем брать данные из массива-источника, массив-назначение, откуда начинаем записывать данные в массив-назначение, сколько ячеек копируем)

 По замерам времени:
 Для первого метода надо считать время только на цикл расчета:
 for (int i = 0; i < size; i++) {
 arr[i] = (float)(arr[i] * Math.sin(0.2f + i / 5) * Math.cos(0.2f + i / 5) * Math.cos(0.4f + i / 2));
 }

 Для второго метода замеряете время разбивки массива на 2, просчета каждого из двух массивов и склейки.
 */
public class Application
{
    private static final int THREADS = 10;
    private static final int SIZE = 10000000;
    private static float[][] step_arr;
    private static int step_threads = 0;

    /**
     * Запуск алгоритма работы с массивами с итерациями от 1 до 10 потоков.
     *
     * @param args
     */
    public static void main( String[] args )
    {
        try {

            Application app = new Application();

            System.out.println("Simple Threads Runable execution:");
            for (int i = 1; i <= THREADS; i++) {
                app.createArrayAsynk(i);
            }
            System.out.println("\n");

            System.out.println("ExecutorService threads execution:");
            for (int i = 1; i <= THREADS; i++) {
                app.createExecutorService(i);
            }

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Вычисление значений внутри массива.
     *
     * @param threads - количество потоков, на которое разбить вычисления.
     */
    private void createArrayAsynk(int threads) {

        float[] arr = new float[SIZE];
        for (int i = 0; i < SIZE; i++) {
            arr[i] = 1;
        }

        int step_size = SIZE / threads;
        step_arr = new float[threads][step_size];

        long startTime = System.currentTimeMillis();

        for (int i = 0; i < threads; i++) {
            System.arraycopy(arr, step_size * i, step_arr[i], 0, step_size);
        }

        for (int i = 0; i < threads; i++) {
            myAsynkTask(i);
        }

        for (int i = 0; i < threads; i++) {
            System.arraycopy(step_arr[i], 0, arr, step_size * i, step_size);
        }

        long endTime = System.currentTimeMillis();
        System.out.println(threads + " thread execution method timing is: " + (endTime - startTime) + " ms.");
    }

    private void createExecutorService(int threads) throws InterruptedException {

        step_threads = 0;
        float[] arr = new float[SIZE];
        for (int i = 0; i < SIZE; i++) {
            arr[i] = 1;
        }

        int step_size = SIZE / threads;
        step_arr = new float[threads][step_size];

        long startTime = System.currentTimeMillis();

        for (int i = 0; i < threads; i++) {
            System.arraycopy(arr, step_size * i, step_arr[i], 0, step_size);
        }

        final ExecutorService service = Executors.newFixedThreadPool(threads);
        for (int i = 0; i < threads; i++) {
            final int iter = i;
            service.submit(new Runnable() {
                public void run() {
                    calculate(step_arr[iter]);
                    step_threads++;
                }
            });
        }

        // ждем завершения тредов внутри ExecutorService для замеров времени
        while (step_threads < threads) {
            Thread.sleep(100);
        }

        for (int i = 0; i < threads; i++) {
            System.arraycopy(step_arr[i], 0, arr, step_size * i, step_size);
        }

        long endTime = System.currentTimeMillis();
        System.out.println(threads + " thread execution method timing is: " + (endTime - startTime) + " ms.");
    }

    /**
     * Запуск в подпотоке функции-вычислителя
     *
     * @param iter
     */
    private static void myAsynkTask(final int iter) {
        final Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                calculate(step_arr[iter]);
            }
        });
        thread.run();
    }

    /**
     * Функция-вычислитель
     *
     * @param arr
     * @return
     */
    private static float[] calculate(float[] arr) {
        for (int i = 0; i < arr.length; i++) {
            arr[i] = (float)(arr[i] * Math.sin(0.2f + i / 5) * Math.cos(0.2f + i / 5) * Math.cos(0.4f + i / 2));
        }
        return arr;
    }
}
