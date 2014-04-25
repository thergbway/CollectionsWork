package task;

import java.util.*;

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
                BlackBox<Integer> blackBox = new BlackBox<Integer>(k - 1);
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

    private class BlackBox<E extends Number> {
        List<E> numList = new LinkedList<>();
        int k;

        public BlackBox(int k) {
            this.k = k;
        }

        public void add(E number) {
            numList.add(number);
            Collections.sort(numList, new Comparator<E>() {
                @Override
                public int compare(E o1, E o2) {
                    if (o1.doubleValue() == o2.doubleValue())
                        return 0;
                    if (o1.doubleValue() < o2.doubleValue())
                        return -1;
                    else
                        return +1;
                }
            });
        }

        public E getKNumber() {
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