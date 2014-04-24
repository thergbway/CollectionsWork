package task;

import java.util.*;

public class Task22 extends Task {
    private static final String TASK_INFO = "На прямой гоночной трассе стоит N автомобилей, " +
            "для каждого из которых известны начальное положение и скорость. " +
            "Определить, сколько произойдет обгонов.\n\n\n";
    private static final double MAX_CAR_COORDINATE = 100.0;
    private static final double MAX_CAR_SPEED = 100.0;

    public Task22() {
        super("#22", TASK_INFO);
    }

    @Override
    public void execute() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                appendText("Введите число N (>0)\n");
                int n = readInt();

                //creating cars map
                Random r = new Random();
                Map<Double, Double> carsMap = new HashMap<Double, Double>();
                for (int i = 0; i < n; i++) {
                    while (carsMap.put(r.nextDouble() * MAX_CAR_COORDINATE, r.nextDouble() * MAX_CAR_SPEED) != null) ;
                }

                appendText("Автомобили на трассе (начальная координата, скорость):\n");
                List<Double> carCoordinates = new LinkedList<Double>(carsMap.keySet());
                Collections.sort(carCoordinates);
                for (int i = 0; i < carCoordinates.size(); i++) {
                    Double carCoordinate = carCoordinates.get(i);
                    Double carSpeed = carsMap.get(carCoordinate);
                    appendText(String.format("%4.2f --- %4.2f\n", carCoordinate, carSpeed));
                }

                //count overtakes
                int overtakeCount = 0;
                for (int i = 0; i < carCoordinates.size(); i++) {
                    Double carCoordinate = carCoordinates.get(i);
                    Double carSpeed = carsMap.get(carCoordinate);

                    for (int j = 0; j < carCoordinates.size(); j++) {
                        Double currCarCoordinate = carCoordinates.get(j);
                        Double currCarSpeed = carsMap.get(currCarCoordinate);
                        if (currCarCoordinate > carCoordinate
                                && currCarSpeed < carSpeed)
                            ++overtakeCount;
                    }
                }

                appendText("Количество обгонов: " + overtakeCount + "\n");
            }
        }).start();
    }
}