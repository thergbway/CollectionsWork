package task;

public class Task15 extends Task {
    private static final String TASK_INFO = "На базе коллекций реализовать структуру хранения чисел с поддержкой следующих операций:\n" +
            "•   добавление/удаление числа;\n" +
            "•   поиск числа, наиболее близкого к заданному (т.е. модуль разницы минимален).\n\n\n";

    public Task15() {
        super("#15", TASK_INFO);
    }

    @Override
    public void execute() {
        new Thread(new Runnable() {
            @Override
            public void run() {
            }
        }).start();
    }
}