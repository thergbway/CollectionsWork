package task;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;

import static java.nio.file.Files.readAllLines;

public class Task3 extends Task {
    private static final String TASK_INFO = "Занести стихотворения одного автора в список. Провести сортировку по возрастанию длин строк.\n\n";

    public Task3() {
        super("#3", TASK_INFO);
    }

    @Override
    public void execute() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                File file = null;
                appendText("Пожалуйста, введите путь к файлу стихотворения, например, content/task3/lyrics.txt\n");

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

                //sort and show
                Collections.sort(strings, new Comparator<String>() {
                    @Override
                    public int compare(String o1, String o2) {
                        if (o2 == null || o1 == null)
                            throw new IllegalArgumentException("String to be compared is null");
                        if (o1.length() > o2.length())
                            return 1;
                        else if (o1.length() == o2.length())
                            return 0;
                        else
                            return -1;
                    }
                });

                appendText("\nСтроки отсортированы: \n\n");
                for (String s : strings) {
                    if (s.trim().equals(""))
                        appendText("(пустая строка)");
                    else
                        appendText(s);
                    appendText("\n");
                }
                appendText("\n");

            }
        }).start();
    }
}