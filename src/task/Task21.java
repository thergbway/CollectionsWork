package task;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

public class Task21 extends Task {
    private static final String TASK_INFO = "Реализовать структуру \"черный ящик\", хранящую множество чисел и " +
            "имеющую внутреннее число K. Структура должна поддерживать операции добавления числа в множество и " +
            "возвращение K-го по минимальности числа из множества.\n\n\n";
    private static final int MAX_VALUE = 20;

    public Task21() {
        super("#21", TASK_INFO);
    }

    @Override
    public void execute() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                appendText("Пожалуйста, введите начальное количество элементов в структуре (>0):\n");
                int numCount = readInt();
                appendText("Введено: " + numCount + "\n");
                appendText("Пожалуйста, число k (>0):\n");
                int k = readInt();
                appendText("Введено: " + k + "\n");
                appendText("Пожалуйста, введите количество элементов для добавления в структуру (>0):\n");
                int numToAdd = readInt();
                appendText("Введено: " + numToAdd + "\n");
                Random r = new Random();
                BlackBox blackBox = new BlackBox(k - 1);
                for (int i = 0; i < numCount; i++) {
                    blackBox.add(r.nextInt(MAX_VALUE));
                }
                appendText("Полученная структура: \n");
                appendText(blackBox.toString() + "\n");
                Integer kValue = blackBox.getKNumber();
                if (kValue != null)
                    appendText("k-ый элемент:" + kValue + "\n");
                else
                    appendText("k-го элемента нет в структуре: размер структуры слишком мал\n");

                for (int i = 0; i < numToAdd; i++) {
                    blackBox.add(r.nextInt(MAX_VALUE));
                }
                appendText("Полученная структура после добавления элементов: \n");
                appendText(blackBox.toString() + "\n");
                kValue = blackBox.getKNumber();
                if (kValue != null)
                    appendText("k-ый элемент:" + kValue + "\n");
                else
                    appendText("k-го элемента нет в структуре: размер структуры слишком мал\n");
            }
        }).start();
    }

    /**
     * Структура, хранящая в себе числа
     */
    private class BlackBox {
        List<Integer> numList = new LinkedList<>();
        int k;

        /**
         * Конструктор
         *
         * @param k число к из задания
         */
        public BlackBox(int k) {
            this.k = k;
        }

        /**
         * Добавить элемент в структуру
         *
         * @param number элемент
         */
        public void add(Integer number) {
            numList.add(number);
            Collections.sort(numList);
        }

        /**
         * Взять k-ый элемент из структуры
         *
         * @return k-ый элемент
         */
        public Integer getKNumber() {
            if (k >= numList.size())
                return null;
            else
                return numList.get(k);
        }

        @Override
        public String toString() {
            return numList.toString();
        }
    }
}