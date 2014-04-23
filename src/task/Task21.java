package task;

public class Task21 extends Task {
    private static final String TASK_INFO = "Реализовать структуру \"черный ящик\", хранящую множество чисел и " +
            "имеющую внутреннее число K. Структура должна поддерживать операции добавления числа в множество и " +
            "возвращение K-го по минимальности числа из множества.\n\n\n";

    public Task21() {
        super("#21", TASK_INFO);
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