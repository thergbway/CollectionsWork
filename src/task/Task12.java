package task;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class Task12 extends Task {
    private static final String TASK_INFO = "В кругу стоят N человек, пронумерованных от 1 до N. " +
            "При ведении счета по кругу вычеркивается каждый второй человек, пока не останется один. " +
            "Составить две программы, моделирующие процесс. Одна из программ должна использовать класс ArrayList, " +
            "а вторая – LinkedList. Какая из двух программ работает быстрее? Почему?\n\n";

    public Task12() {
        super("#12", TASK_INFO);
    }

    @Override
    public void execute() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                appendText("Пожалуйста, введите число N >= 1\n");
                int n = readInt();

                //создаем 2 списка одинакого содержания
                LinkedList<Integer> peopleLinkedList = new LinkedList<Integer>();
                for (int i = 1; i <= n; ++i) {
                    peopleLinkedList.add(i);
                }
                ArrayList<Integer> peopleArrayList = new ArrayList<Integer>(peopleLinkedList);

                long startTime = System.currentTimeMillis();
                modelTaskProcess(peopleLinkedList);
                appendText("LinkedList: " + (System.currentTimeMillis() - startTime) + "ms\n");
                startTime = System.currentTimeMillis();
                modelTaskProcess(peopleArrayList);
                appendText("ArrayList: " + (System.currentTimeMillis() - startTime) + "ms\n");
                appendText("При большом числе элементов ArrayList сильно проигрывает по производительности " +
                        "в сравнении с LinkedList. Это связано c дороговизной операции удаления из середины" +
                        " списка у ArrayList\n");
            }
        }).start();
    }

    private void modelTaskProcess(List<Integer> listToProcess) {

        boolean isRemoved = true;
        while (listToProcess.size() > 1) {
            Iterator<Integer> it = listToProcess.iterator();
            while (it.hasNext()) {
                Integer next = it.next();
                if (isRemoved) {
                    it.remove();
                    isRemoved = false;//следующий элемент не удаляем
                } else {
                    isRemoved = true;
                }
            }
        }
    }
}