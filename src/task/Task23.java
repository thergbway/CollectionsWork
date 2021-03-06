package task;

import java.util.*;

public class Task23 extends Task {
    private static final String TASK_INFO = "На прямой гоночной трассе стоит N автомобилей, " +
            "для каждого из которых известны начальное положение и скорость. Вывести первые K обгонов.\n\n\n";
    private static final double MAX_CAR_COORDINATE = 100.0;
    private static final double MAX_CAR_SPEED = 100.0;

    public Task23() {
        super("#23", TASK_INFO);
    }

    @Override
    public void execute() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                appendText("Введите число N (>0)\n");
                int n = readInt();
                appendText("Введите число K (>0)\n");
                int k = readInt();

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

                //key - time, value - start coord of the car
                Map<Double, Double> overtakes = new HashMap<Double, Double>();
                for (int i = 0; i < carCoordinates.size(); i++) {
                    Double carCoordinate = carCoordinates.get(i);
                    Double carSpeed = carsMap.get(carCoordinate);

                    for (int j = 0; j < carCoordinates.size(); j++) {
                        Double currCarCoordinate = carCoordinates.get(j);
                        Double currCarSpeed = carsMap.get(currCarCoordinate);
                        if (currCarCoordinate > carCoordinate
                                && currCarSpeed < carSpeed) {
                            overtakes.put((currCarCoordinate - carCoordinate) / (carSpeed - currCarSpeed), carCoordinate);
                        }
                    }
                }

                appendText("Первые К обгонов(время для обгона, нач. координата обгоняющей машины):\n");
                List<Double> overtakeTimes = new LinkedList<Double>(overtakes.keySet());
                Collections.sort(overtakeTimes);
                int overtakesToShow = k;
                if (k > overtakeTimes.size())
                    overtakesToShow = overtakeTimes.size();

                for (int i = 0; i < overtakesToShow; i++) {
                    Double time = overtakeTimes.get(i);
                    Double startPosition = overtakes.get(time);
                    appendText(String.format("%4.2f --- %4.2f\n", time, startPosition));
                }
            }
        }).start();
    }
}