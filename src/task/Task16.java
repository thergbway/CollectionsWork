package task;

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
            }
        }).start();
    }
}