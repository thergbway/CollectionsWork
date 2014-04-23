package task;

public class Task22 extends Task {
    private static final String TASK_INFO = "На прямой гоночной трассе стоит N автомобилей, " +
            "для каждого из которых известны начальное положение и скорость. " +
            "Определить, сколько произойдет обгонов.\n\n\n";

    public Task22() {
        super("#22", TASK_INFO);
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