package task;

import java.util.LinkedList;
import java.util.List;

public class Task8 extends Task {
    private static final String TASK_INFO = "Умножить два многочлена заданной степени, если коэффициенты многочленов хранятся в различных списках.\n\n";

    public Task8() {
        super("#8", TASK_INFO);
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
                List<Integer> coeffListA = new LinkedList<Integer>();

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
                    coeffListA.addAll(tempList);
                }
                appendText("Введен многочлен А \"" + polinomialToString(coeffListA) + "\"\n\n");

                appendText("Пожалуйста, введите коэффициенты многочлена B - " +
                        "целые числа - коэффициенты для увеличивающихся степеней переменных" +
                        " в строку через пробел, например, \"2 3 4 5\" соответствует многочлену " +
                        "\"2 + 3x + 4x^2 + 5x^3\"\n");
                List<Integer> coeffListB = new LinkedList<Integer>();

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
                    coeffListB.addAll(tempList);
                }
                appendText("Введен многочлен B \"" + polinomialToString(coeffListB) + "\"\n\n");

                //mult
                List<Integer> resultPolinomial = new LinkedList<Integer>();
                for (int i = 0; i < coeffListA.size(); i++) {
                    Integer tempCoeffA = coeffListA.get(i);

                    List<Integer> tempPolinomial = multMonomial(tempCoeffA, i, coeffListB);
                    resultPolinomial = sumPolinomial(resultPolinomial, tempPolinomial);
                }
                appendText("Результат произведения А*В \"" + polinomialToString(multPolinomial(coeffListA, coeffListB)) + "\"\n\n");
            }
        }).start();
    }

    /**
     * Преобразовать многочлен в строку
     *
     * @param coeffList преобразуемый многочлен
     * @return полученная строка
     */
    private String polinomialToString(List<Integer> coeffList) {
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < coeffList.size() - 1; i++) {
            sb.append(coeffList.get(i) + "x^" + i + " + ");
        }
        sb.append(coeffList.get(coeffList.size() - 1) + "x^" + (coeffList.size() - 1));

        return sb.toString();
    }

    /**
     * Суммирование многочленов
     *
     * @param polinomialA первый многочлен
     * @param polinomialB второй многочлен
     * @return полученная сумма
     */
    private List<Integer> sumPolinomial(List<Integer> polinomialA, List<Integer> polinomialB) {
        List<Integer> bigger;
        List<Integer> smaller;

        if (polinomialA.size() >= polinomialB.size()) {
            bigger = polinomialA;
            smaller = polinomialB;
        } else {
            bigger = polinomialB;
            smaller = polinomialA;
        }

        for (int i = 0; i < smaller.size(); i++) {
            Integer tempCoeffSmaller = smaller.get(i);
            bigger.set(i, tempCoeffSmaller + bigger.get(i));
        }

        return new LinkedList<Integer>(bigger);
    }

    /**
     * Умножение одночлена на многочлен
     *
     * @param coeffMono  коэффициент одночлена
     * @param powMono    степень одночлена
     * @param polinomial многочлен
     * @return полученное произведение многочлена и одночлена
     */
    private List<Integer> multMonomial(int coeffMono, int powMono, List<Integer> polinomial) {
        List<Integer> result = new LinkedList<Integer>();

        int resultPow = polinomial.size() - 1 + powMono;
        for (int i = 0; i < resultPow + 1; ++i)
            result.add(0);

        for (int tempPowPolinomial = 0; tempPowPolinomial < polinomial.size(); tempPowPolinomial++) {
            Integer tempCoeffPolinomial = polinomial.get(tempPowPolinomial);

            int newCoeff = coeffMono * tempCoeffPolinomial;
            int newPow = tempPowPolinomial + powMono;

            result.set(newPow, result.get(newPow) + newCoeff);
        }

        return result;
    }

    /**
     * Умножение полиномов
     * @param polinomialA первый полином
     * @param polinomialB второй полином
     * @return произведение полиномов
     */
    private List<Integer> multPolinomial(List<Integer> polinomialA, List<Integer> polinomialB) {
        List<Integer> result = new LinkedList<Integer>();

        for (int i = 0; i < polinomialA.size(); i++) {
            Integer tempCoeffA = polinomialA.get(i);
            List<Integer> monoResultPolinomial = multMonomial(tempCoeffA, i, polinomialB);
            result = sumPolinomial(monoResultPolinomial, result);
        }

        return result;
    }
}