package task;

public class Task23 extends Task {
    private static final String TASK_INFO = "На прямой гоночной трассе стоит N автомобилей, " +
            "для каждого из которых известны начальное положение и скорость. Вывести первые K обгонов.\n\n\n";

    public Task23() {
        super("#23", TASK_INFO);
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