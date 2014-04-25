package task;

import java.util.LinkedList;
import java.util.List;

public class Task5 extends Task {
    private static final String TASK_INFO = "Списки I(1..n) и U(1..n) содержат результаты n-измерений тока и " +
            "напряжения на неизвестном сопротивлении R. Найти приближенное число R методом наименьших квадратов.\n\n";

    public Task5() {
        super("#5", TASK_INFO);
    }

    @Override
    public void execute() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                appendText("Пожалуйста, введите результаты измерения тока - целые числа в строку через пробел\n");
                List<Integer> listI = new LinkedList<Integer>();

                boolean isCorrectSet = false;
                outer:
                while (!isCorrectSet) {
                    String s = readLine().trim();
                    String[] strings = s.split("\\s");
                    List<Integer> tempSet = new LinkedList<Integer>();
                    for (String str : strings) {
                        try {
                            tempSet.add(Integer.parseInt(str));
                        } catch (NumberFormatException e) {
                            appendText("Неправильный ввод, попробуйте ещё раз\n");
                            continue outer;
                        }
                    }
                    isCorrectSet = true;
                    listI.addAll(tempSet);
                }
                appendText("Введено I \"" + listI + "\"\n");

                appendText("Пожалуйста, введите результаты измерения напряжения - целые числа в строку через пробел\n");
                List<Integer> listU = new LinkedList<Integer>();

                isCorrectSet = false;
                outer:
                while (!isCorrectSet) {
                    String s = readLine().trim();
                    String[] strings = s.split("\\s");
                    List<Integer> tempSet = new LinkedList<Integer>();
                    if (strings.length != listI.size()) {
                        appendText("Количество измерений напряжения должно быть равно " + listI.size()
                                + ". Попробуйте ещё раз\n");
                        continue;
                    }
                    for (String str : strings) {
                        try {
                            tempSet.add(Integer.parseInt(str));
                        } catch (NumberFormatException e) {
                            appendText("Неправильный ввод, попробуйте ещё раз\n");
                            continue outer;
                        }
                    }
                    isCorrectSet = true;
                    listU.addAll(tempSet);
                }
                appendText("Введено U \"" + listU + "\"\n");
                appendText("Сопротивление, найденное методом наименьших квадратов: " + computeR(listI, listU));

            }
        }).start();
    }

    /**
     * Поиск сопротивления методом наименьших квадратов
     *
     * @param i список результатов измерения тока
     * @param u список результатов измерения напряжения
     * @return найденное сопротивление
     * @throws IllegalArgumentException если размеры списков не равны
     */
    private double computeR(List<Integer> i, List<Integer> u) {
        if (i.size() != u.size())
            throw new IllegalArgumentException("i.size() != u.size()");

        double sumIU = 0.0;
        double sumII = 0.0;

        for (int j = 0; j < i.size(); j++) {
            Integer currI = i.get(j);
            sumII += currI * currI;
            sumIU += currI * u.get(j);
        }

        return sumIU / sumII;
    }
}