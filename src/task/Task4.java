package task;

import java.util.HashSet;
import java.util.Set;

public class Task4 extends Task {
    private static final String TASK_INFO = "Определить множество на основе множества целых чисел. Создать методы для определения пересечения и объединения множеств.\n\n";

    public Task4() {
        super("#4", TASK_INFO);
    }

    /**
     * Поиск объединения множеств
     *
     * @param A первое множество
     * @param B второе множество
     * @return объединение множеств A и B
     */
    private Set<Integer> getUnion(Set<Integer> A, Set<Integer> B) {
        Set<Integer> result = new HashSet<Integer>(A);
        result.addAll(B);
        return result;
    }

    /**
     * Вычисление пересечения множеств
     *
     * @param A первое множество
     * @param B второе множество
     * @return пересечение множеств A и B
     */
    private Set<Integer> getIntersection(Set<Integer> A, Set<Integer> B) {
        Set<Integer> result = new HashSet<Integer>();

        for (Integer i : A) {
            if (!B.add(i))
                result.add(i);
        }
        return result;
    }

    @Override
    public void execute() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                appendText("Пожалуйста, введите множество А целых чисел в строку через пробел\n");
                Set<Integer> setA = new HashSet<Integer>();

                boolean isCorrectSet = false;
                outer:
                while (!isCorrectSet) {
                    String s = readLine().trim();
                    String[] strings = s.split("\\s");
                    Set<Integer> tempSet = new HashSet<Integer>();
                    for (String str : strings) {
                        try {
                            tempSet.add(Integer.parseInt(str));
                        } catch (NumberFormatException e) {
                            appendText("Неправильный ввод, попробуйте ещё раз\n");
                            continue outer;
                        }
                    }
                    isCorrectSet = true;
                    setA.addAll(tempSet);
                }
                appendText("Введено A \"" + setA + "\"\n");

                appendText("Пожалуйста, введите множество B целых чисел в строку через пробел\n");
                Set<Integer> setB = new HashSet<Integer>();
                isCorrectSet = false;
                outer:
                while (!isCorrectSet) {
                    String s = readLine().trim();
                    String[] strings = s.split("\\s");
                    Set<Integer> tempSet = new HashSet<Integer>();
                    for (String str : strings) {
                        try {
                            tempSet.add(Integer.parseInt(str));
                        } catch (NumberFormatException e) {
                            appendText("Неправильный ввод, попробуйте ещё раз\n");
                            continue outer;
                        }
                    }
                    isCorrectSet = true;
                    setB.addAll(tempSet);
                }
                appendText("Введено B \"" + setB + "\"\n");

                appendText("Объединение множеств А и В \"" + getUnion(setA, setB) + "\"\n");
                appendText("Пересечение множеств А и В \"" + getIntersection(setA, setB) + "\"\n");

            }
        }).start();
    }
}