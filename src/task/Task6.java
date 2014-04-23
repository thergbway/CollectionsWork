package task;

import java.util.*;

public class Task6 extends Task {
    private static final String TASK_INFO = "С использованием множества выполнить попарное суммирование" +
            " произвольного конечного ряда чисел по следующим правилам: на первом этапе суммируются попарно " +
            "рядом стоящие числа, на втором этапе суммируются результаты первого этапа и т.д. до тех пор, " +
            "пока не останется одно число.\n\n";

    public Task6() {
        super("#6", TASK_INFO);
    }

    @Override
    public void execute() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                appendText("Пожалуйста, введите произвольный конечный ряд - целые числа в строку через пробел\n");
                List<Integer> numberList = new LinkedList<Integer>();

                boolean isCorrectSet = false;
                outer:
                while (!isCorrectSet) {
                    String s = readLine().trim();
                    String[] strings = s.split("\\s");
                    List<Integer> tempList = new LinkedList<Integer>();
                    for (String str : strings) {
                        try {
                            tempList.add(Integer.parseInt(str));
                        } catch (NumberFormatException e) {
                            appendText("Неправильный ввод, попробуйте ещё раз\n");
                            continue outer;
                        }
                    }
                    isCorrectSet = true;
                    numberList.addAll(tempList);
                }
                appendText("Введены числа \"" + numberList + "\"\n");

                while(numberList.size() > 1)
                    numberList = makeSumStep(numberList);

                appendText("Результат \"" + numberList + "\"\n");
            }
        }).start();
    }

    /**
     * Суммирование попарное рядом стоящих чисел
     *
     * @param numbers список для суммирования
     * @return полученный список
     */
    private List<Integer> makeSumStep(List<Integer> numbers) {
        List<Integer> result = new LinkedList<Integer>();

        Iterator<Integer> it = numbers.iterator();

        int stepCounter = 0;
        int firstPairElement = 0;
        int secondPairElement = 0;

        while (it.hasNext()) {
            ++stepCounter;
            Integer number = it.next();
            if (stepCounter % 2 == 1) {//первый элемент пары
                firstPairElement = number;
            } else {//второй элемент пары
                secondPairElement = number;
                result.add(firstPairElement + secondPairElement);
            }
        }

        if (stepCounter % 2 == 1) {//остался один элемент без пары
            result.add(firstPairElement);
        }

        return result;
    }
}