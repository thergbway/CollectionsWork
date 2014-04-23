package task;

import java.util.*;

public class Task7 extends Task {
    private static final String TASK_INFO = "Сложить два многочлена заданной степени, " +
            "если коэффициенты многочленов хранятся в объекте HashMap.\n\n";

    public Task7() {
        super("#7", TASK_INFO);
    }

    @Override
    public void execute() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                appendText("Пожалуйста, введите коэффициенты многочлена A - " +
                        "целые числа - коэффициенты для увеличивающихся степеней переменных" +
                        " в строку через пробел, например, \"2 3 4 5\" соответствует многочлену " +
                        "\"2 + 3x + 4x^2 + 5x^3\"\n");
                Map<Integer, Integer> coeffMapA = new HashMap<Integer, Integer>();

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
                    for (int i = 0; i < tempList.size(); i++) {
                        Integer number = tempList.get(i);
                        coeffMapA.put(i, number);
                    }
                }
                appendText("Введен многочлен А \"" + polinomialToString(coeffMapA) + "\"\n\n");

                appendText("Пожалуйста, введите коэффициенты многочлена B - " +
                        "целые числа - коэффициенты для увеличивающихся степеней переменных" +
                        " в строку через пробел, например, \"2 3 4 5\" соответствует многочлену " +
                        "\"2 + 3x + 4x^2 + 5x^3\"\n");
                Map<Integer, Integer> coeffMapB = new HashMap<Integer, Integer>();

                isCorrectSet = false;
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
                    for (int i = 0; i < tempList.size(); i++) {
                        Integer number = tempList.get(i);
                        coeffMapB.put(i, number);
                    }
                }
                appendText("Введен многочлен B \"" + polinomialToString(coeffMapB) + "\"\n\n");

                //sum
                Map<Integer, Integer> result = new HashMap<Integer, Integer>();
                Set<Integer> keysA = coeffMapA.keySet();
                Set<Integer> keysB = coeffMapB.keySet();
                List<Integer> keysAB = new LinkedList<Integer>(keysA);
                keysAB.addAll(keysB);

                for (int i = 0; i < keysAB.size(); i++) {
                    int number = keysAB.get(i);
                    int tempCoeffA = 0;
                    int tempCoeffB = 0;
                    if(coeffMapA.get(number) != null)
                        tempCoeffA = coeffMapA.get(number);
                    if(coeffMapB.get(number) != null)
                        tempCoeffB = coeffMapB.get(number);

                    result.put(number, tempCoeffA + tempCoeffB);
                }

                appendText("Сумма многочленов А и В \"" + polinomialToString(result) + "\"\n");
            }
        }).start();
    }

    /**
     * Преобразовать многочлен в строку
     * @param coeffMap преобразуемый многочлен
     * @return полученная строка
     */
    private String polinomialToString(Map<Integer, Integer> coeffMap) {
        StringBuilder sb = new StringBuilder();

        List<Integer> keys = new LinkedList<Integer>(coeffMap.keySet());
        Collections.sort(keys);
        for (int i = 0; i < keys.size() - 1; i++) {
            Integer number = keys.get(i);
            sb.append(coeffMap.get(number) + "x^" + number + " + ");
        }
        int lastNumber = keys.get(keys.size() - 1);
        sb.append(coeffMap.get(lastNumber) + "x^" + lastNumber);

        return sb.toString();
    }
}