package task;

import geometry.Line;
import geometry.Point2D;

import java.util.Map;
import java.util.Random;
import java.util.TreeMap;

public class Task19 extends Task {
    private static final String TASK_INFO = "На плоскости задано N отрезков. Найти точку пересечения двух отрезков, " +
            "имеющую минимальную абсциссу. Использовать класс TreeMap.\n\n\n";
    private static final int MAX_POINT_COORDINATE = 100;

    public Task19() {
        super("#19", TASK_INFO);
    }

    @Override
    public void execute() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                appendText("Пожалуйста, введите количество отрезков для генерации:\n");
                int linesCount = readInt();

                Random r = new Random();
                Map<Integer, Line> linesMap = new TreeMap<Integer, Line>();
                for (int i = 0; i < linesCount; i++) {
                    linesMap.put(i,
                            new Line(new Point2D(r.nextInt(MAX_POINT_COORDINATE), r.nextInt(MAX_POINT_COORDINATE)),
                                    new Point2D(r.nextInt(MAX_POINT_COORDINATE), r.nextInt(MAX_POINT_COORDINATE)))
                    );
                }
                //write out lines
                appendText("Сгенерированные отрезки:\n");
                for (int i = 0; i < linesCount; i++) {
                    Line currLine = linesMap.get(i);
                    appendText("Линия " + i + ": " + currLine + "\n");
                }

                //find crossing lines
                int minX = Integer.MAX_VALUE;
                int minY = Integer.MAX_VALUE;
                int indexOfFirstLine = -1;
                int indexOfSecondLine = -1;

                for (int i = 0; i < linesCount; i++) {
                    for (int j = 0; j < linesCount; j++) {
                        if (i == j)
                            continue;

                        Line line1 = linesMap.get(i);
                        Line line2 = linesMap.get(j);

                        int x1 = line1.p1.x;
                        int y1 = line1.p1.y;
                        int x2 = line1.p2.x;
                        int y2 = line1.p2.y;

                        int x3 = line2.p1.x;
                        int y3 = line2.p1.y;
                        int x4 = line2.p2.x;
                        int y4 = line2.p2.y;

                        //проверим, не параллельны ли они
                        boolean isParallel = (x2 - x1) * (y4 - y3) == (y2 - y1) * (x4 - x3);
                        if (isParallel)//параллельны => нет общих точек
                            continue;

                        double crossX = (double) ((x1 * y2 - x2 * y1) * (x4 - x3) - (x3 * y4 - x4 * y3) * (x2 - x1)) / (double) ((y1 - y2) * (x4 - x3) - (y3 - y4) * (x2 - x1));
                        double crossY = (double) ((y3 - y4) * crossX - (x3 * y4 - x4 * y3)) / (double) (x4 - x3);

                        crossX = Math.abs(crossX);
                        crossY = Math.abs(crossY);

                        boolean isCrossing = (((x1 <= crossX) && (x2 >= crossX) && (x3 <= crossX) && (x4 >= crossX)) ||
                                ((y1 <= crossY) && (y2 >= crossY) && (y3 <= crossY) && (y4 >= crossY)));
                        if (!isCrossing)//точка пересечения есть только у соответствующих прямых, но не у отрезков
                            continue;
                        else {//точка пересечения есть
                            if (crossX <= minX) {//у новой точки пересечения абсцисса меньше => сделаем пересечение новым
                                minX = (int) crossX;
                                minY = (int) crossY;
                                indexOfFirstLine = i;
                                indexOfSecondLine = j;
                            }
                        }
                    }
                }

                //write result
                if (indexOfFirstLine == -1) {
                    //no crossing
                    appendText("Нет точек пересечения\n");
                    return;
                } else {
                    //write out crossing lines info
                    appendText("Найденное пересечение:\n");
                    appendText("Линия " + indexOfFirstLine + ": " + linesMap.get(indexOfFirstLine) + "\n");
                    appendText("Линия " + indexOfSecondLine + ": " + linesMap.get(indexOfSecondLine) + "\n");
                    appendText("Точка пересечения: (" + minX + ", " + minY + ")");
                    return;
                }
            }
        }).start();
    }
}