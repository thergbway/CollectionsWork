package task;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

public class Task13 extends Task {
    private static final String TASK_INFO = "Задан список целых чисел и число X. " +
            "Не используя вспомогательных объектов и не изменяя размера списка, " +
            "переставить элементы списка так, чтобы сначала шли числа, не превосходящие X, " +
            "а затем числа, большие X.\n\n";
    private static final int MAX_LIST_ELEMENT_VALUE = 100;

    public Task13() {
        super("#13", TASK_INFO);
    }

    @Override
    public void execute() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                appendText("Пожалуйста, введите размер списка\n");
                int listSize = readInt();
                appendText("Введено " + listSize + "\n");

                appendText("Пожалуйста, введите индекс элемента Х не больше, чем размер списка. Первому элементу в списке соответствует индекс 1\n");
                boolean isFirstTime = true;
                int xIndex = -1;
                while (xIndex < 1 || xIndex > listSize) {
                    if (!isFirstTime)
                        appendText("Введено неправильное число, попробуйте ещё раз\n");
                    xIndex = readInt();
                    isFirstTime = false;
                }
                appendText("Введено " + xIndex + "\n");
                --xIndex;

                //generate random list
                List<Integer> numbers = new LinkedList<Integer>();
                Random rand = new Random();
                for (int i = 0; i < listSize; ++i) {
                    numbers.add(rand.nextInt(MAX_LIST_ELEMENT_VALUE));
                }
                int xValue = numbers.get(xIndex);

                appendText("Список:\n");
                appendText(numbers + "\n");
                appendText("Число X: " + xValue + "\n");

                Collections.sort(numbers);
                appendText("Список переставленных элементов:\n");
                appendText(numbers + "\n");
                appendText("Число Х теперь находится на позиции: " + (numbers.indexOf(xValue) + 1));
            }
        }).start();
    }
}