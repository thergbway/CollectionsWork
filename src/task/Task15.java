package task;

import java.util.LinkedList;
import java.util.List;
import java.util.Random;

public class Task15 extends Task {
    private static final String TASK_INFO = "На базе коллекций реализовать структуру хранения чисел с поддержкой следующих операций:\n" +
            "•   добавление/удаление числа;\n" +
            "•   поиск числа, наиболее близкого к заданному (т.е. модуль разницы минимален).\n\n\n";
    private static final int MAX_HOLDING_VALUE = 100;

    public Task15() {
        super("#15", TASK_INFO);
    }

    @Override
    public void execute() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                appendText("Пожалуйста, введите количество чисел, которые будут добавлены в коллекцию > 0\n");
                int numberCount = readInt();
                appendText("Введено " + numberCount + "\n");

                NumbersHolder<Integer> numbersHolder = new NumbersHolder<Integer>();
                Random r = new Random();
                for (int i = 0; i < numberCount; i++) {
                    numbersHolder.addNumber(r.nextInt(MAX_HOLDING_VALUE));
                }
                appendText("Полученная структура:\n");
                appendText(numbersHolder.toString());
                appendText("\n");

                appendText("Пожалуйста, введите значение элемента (>0), первое вхождение которого должно " +
                        "быть удалено из структуры:\n");
                int numberToRemove = readInt();

                if (numbersHolder.removeNumber(numberToRemove)) {
                    appendText("Удаление прошло успешно\n" +
                            "Новая структура:\n");
                    appendText(numbersHolder.toString());
                    appendText("\n");
                } else
                    appendText("Удаление не было произведено: такого элемента нет в структуре\n");

                appendText("Пожалуйста, введите значение элемента (>0), наиболее близкий элемент к " +
                        "которому будет найден:\n");
                int numberToFind = readInt();
                Integer closestNumber = numbersHolder.findClosest(numberToFind);
                if (closestNumber == null)
                    appendText("Число не найдено, тк структура пуста\n");
                else
                    appendText("Найденное число: " + closestNumber);
            }
        }).start();
    }

    /**
     * Структура, хранящая в себе числа
     *
     * @param <E> тип хранимого числа
     */
    private class NumbersHolder<E extends Number> {
        List<E> list = new LinkedList<>();

        /**
         * Добавляет число в хранилище
         *
         * @param number добавляемое число
         */
        public void addNumber(E number) {
            list.add(number);
        }

        /**
         * Удаляет первый найденный объект, который равен заданному числу, из хранилища
         *
         * @param number число для удаления
         * @return успешно ли было произведено удаление
         */
        public boolean removeNumber(E number) {
            return list.remove(number);
        }

        /**
         * Поиск числа, наиболее близкого к заданному (т.е. модуль разницы минимален)
         *
         * @param number заданное число
         * @return искомое число или null, если коллекция пуста
         */
        public E findClosest(E number) {
            double minDifference = Double.MAX_VALUE;
            int indexOfClosest = -1;

            for (int i = 0; i < list.size(); i++) {
                E e = list.get(i);
                double difference = Math.abs(e.doubleValue() - number.doubleValue());

                if (difference <= minDifference) {
                    minDifference = difference;
                    indexOfClosest = i;
                }
            }

            if (indexOfClosest == -1)
                return null;
            else
                return list.get(indexOfClosest);
        }

        @Override
        public String toString() {
            return list.toString();
        }

        /**
         * Вычисление размера структуры
         *
         * @return результат вычисления
         */
        public int size() {
            return list.size();
        }
    }
}