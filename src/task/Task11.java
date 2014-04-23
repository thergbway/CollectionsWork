package task;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static java.nio.file.Files.readAllLines;

public class Task11 extends Task {
    private static final String TASK_INFO = "Задан файл с текстом на английском языке. Выделить все различные слова. " +
            "Для каждого слова подсчитать частоту его встречаемости. " +
            "Слова, отличающиеся регистром букв, считать различными. Использовать класс HashMap.\n\n";

    public Task11() {
        super("#11", TASK_INFO);
    }

    @Override
    public void execute() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                File file = null;
                appendText("Пожалуйста, введите путь к файлу, например, content/task11/words.txt\n");

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
                appendText("\n\n\n");

                Map<String, Integer> uniqueWords = new HashMap<String, Integer>();
                for (int i = 0; i < strings.size(); i++) {
                    String s = strings.get(i);
                    s = s.toLowerCase();
                    Pattern p = Pattern.compile("\\w+");
                    Matcher m = p.matcher(s);
                    while (m.find()){
                        String nextWord = m.group();
                        Integer count = uniqueWords.get(nextWord);
                        if(count == null)
                            count = 0;
                        uniqueWords.put(nextWord, ++count);
                    }
                }

                appendText("Уникальные слова: \n");
                for (Map.Entry<String, Integer> entry : uniqueWords.entrySet()) {
                    appendText("Word: " + entry.getKey() + "  Frequency: " + entry.getValue() + "\n");
                }
            }
        }).start();
    }
}