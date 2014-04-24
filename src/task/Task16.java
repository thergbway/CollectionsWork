package task;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Collections;
import java.util.InputMismatchException;
import java.util.LinkedList;
import java.util.List;

import static java.nio.file.Files.readAllLines;

public class Task16 extends Task {
    private static final String TASK_INFO = "Во входном файле расположены два набора положительных чисел; " +
            "между наборами стоит отрицательное число. Построить два списка C1 и С2, " +
            "элементы которых содержат соответственно числа 1-го и 2-го набора таким образом, " +
            "чтобы внутри одного списка числа были упорядочены по возрастанию. Затем объединить списки C1 " +
            "и С2 в один упорядоченный список.\n\n\n";

    public Task16() {
        super("#16", TASK_INFO);
    }

    @Override
    public void execute() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                File file = null;
                appendText("Пожалуйста, введите путь к файлу, например, content/task16/in.txt\n");

                boolean isCorrectPath = false;
                while (!isCorrectPath) {
                    String path = readLine().trim();
                    file = new File(path);
                    if (file.isFile())
                        isCorrectPath = true;
                    else
                        appendText("Неправильный путь, попробуйте ещё раз\n");
                }
                appendText("Введено \"" + file.getAbsolutePath() + "\"\n");

                List<String> strings = new LinkedList<String>();
                try {
                    strings.addAll(readAllLines(file.toPath(), StandardCharsets.UTF_8));
                } catch (IOException e) {
                    writeExceptionInfo(e);
                    return;
                }

                appendText("Прочитаны строки: \n\n");
                for (String s : strings) {
                    appendText(s);
                    appendText("\n");
                }
                appendText("\n");

                List<Integer> firstList = new LinkedList<Integer>();
                List<Integer> secondList = new LinkedList<Integer>();

                try {
                    String str = strings.get(0);
                    String[] splittedStrings = str.split("\\s+");
                    boolean toFirstList = true;
                    for (int i = 0; i < splittedStrings.length; i++) {
                        String s = splittedStrings[i];
                        int parsedInt = Integer.parseInt(s);
                        if (parsedInt == 0)
                            throw new InputMismatchException("Wrong file content");
                        if (parsedInt < 0) {
                            toFirstList = false;
                            continue;
                        }

                        if (toFirstList)
                            firstList.add(parsedInt);
                        else
                            secondList.add(parsedInt);
                    }

                    Collections.sort(firstList);
                    Collections.sort(secondList);

                    appendText("Первый список:\n");
                    appendText(firstList + "\n");

                    appendText("Второй список:\n");
                    appendText(secondList + "\n");

                    List<Integer> filledList = new LinkedList<Integer>(firstList);
                    filledList.addAll(secondList);
                    Collections.sort(filledList);

                    appendText("Объединённый список:\n");
                    appendText(filledList + "\n");
                } catch (Exception e) {
                    appendText("Содержимое файла неверно\n");
                    writeExceptionInfo(e);
                    return;
                }
            }
        }).start();
    }
}