package task;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;

import static java.nio.file.Files.readAllLines;

public class Task1 extends Task {
    private static final String TASK_INFO = "Ввести строки из файла, записать их в список. Вывести строки в файл в обратном порядке.\n\n";
    private static final String OUT_FILE_NAME = "content/task1/out.txt";

    public Task1() {
        super("#1", TASK_INFO);
    }

    @Override
    public void execute() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                File file = null;
                appendText("Пожалуйста, введите путь к файлу, например, content/task1/in.txt\n");

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

                //write to file
                try {
                    Path path = FileSystems.getDefault().getPath(OUT_FILE_NAME).toAbsolutePath();
                    FileWriter writer = new FileWriter(path.toFile());
                    appendText("Вывод в обратном порядке в файл: \"" + path);
                    ListIterator<String> it = strings.listIterator(strings.size());
                    appendText("\n");
                    while (it.hasPrevious()) {
                        String s = it.previous();
                        writer.write(s + "\n");
                        appendText("Writing: " + s + "\n");
                    }
                    writer.close();
                } catch (IOException e) {
                    writeExceptionInfo(e);
                    return;
                }
            }
        }).start();
    }
}