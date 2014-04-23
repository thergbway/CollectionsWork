package task;

import java.io.File;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class Task2 extends Task {
    private static final String TASK_INFO = "Создать список из элементов каталога и его подкаталогов.\n\n";

    public Task2() {
        super("#2", TASK_INFO);
    }

    @Override
    public void execute() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                File file = null;
                appendText("Пожалуйста, введите путь к каталогу, например, content/task2/\n");

                boolean isCorrectPath = false;
                while (!isCorrectPath) {
                    String path = readLine().trim();
                    file = new File(path);
                    if (file.isDirectory())
                        isCorrectPath = true;
                    else
                        appendText("Неправильный путь, попробуйте ещё раз\n");
                }
                appendText("Введено \"" + file.getAbsolutePath() + "\"\n");

                List<File> files = getFilesOf(file);
                for (File f : files) {
                    appendText(f.getAbsolutePath() + "\n");
                }
            }
        }).start();
    }

    /**
     * Create all files and directories representing current directory
     *
     * @param file directory to scan
     * @return scanned files and directories
     */
    private List<File> getFilesOf(File file) {
        if (file.isFile())
            return new LinkedList<File>();

        String[] list = file.list();
        LinkedList<String> stringsSource = new LinkedList<String>(Arrays.asList(list));
        LinkedList<File> files = new LinkedList<File>();
        for (String s : stringsSource) {
            File f = new File(file.getPath() + "\\" + s);
            files.add(f);
            files.addAll(getFilesOf(f));
        }
        return files;
    }
}