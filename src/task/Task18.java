package task;

public class Task18 extends Task {
    private static final String TASK_INFO = "На клетчатой бумаге нарисован круг. Вывести в файл описания всех клеток, " +
            "целиком лежащих внутри круга, в порядке возрастания расстояния от клетки до центра круга. " +
            "Использовать класс PriorityQueue.\n\n\n";

    public Task18() {
        super("#18", TASK_INFO);
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