package task;

import geometry.Line;
import geometry.Point2D;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.util.*;

public class Task17 extends Task {
    private static final String TASK_INFO = "На плоскости задано N точек. Вывести в файл описания всех прямых, " +
            "которые проходят более чем через 2 точки из заданных. Для каждой прямой указать, " +
            "через сколько точек она проходит. Использовать класс HashMap.\n\n\n";
    private static final int MAX_POINT_COORDINATE = 10;
    private static final String OUTPUT_FILE_NAME = "content/task17/out.txt";

    public Task17() {
        super("#17", TASK_INFO);
    }

    @Override
    public void execute() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                appendText("Пожалуйста, введите количество точек для генерации:\n");
                int pointsCount = readInt();

                if (pointsCount > (MAX_POINT_COORDINATE + 1) * (MAX_POINT_COORDINATE + 1)) {
                    appendText("Введено слишком большое число, перезапустите задание\n");
                    return;
                }

                Set<Point2D> pointsSet = new HashSet<Point2D>();
                Random r = new Random();
                while (pointsSet.size() < pointsCount) {
                    pointsSet.add(new Point2D(r.nextInt(MAX_POINT_COORDINATE + 1), r.nextInt(MAX_POINT_COORDINATE + 1)));
                }
                List<Point2D> pointsList = new LinkedList<Point2D>(pointsSet);

                //show generated points
                appendText("Созданные точки:\n");
                for (int i = 0; i < pointsList.size(); i++) {
                    Point2D point2D = pointsList.get(i);
                    appendText(point2D.toString() + "\n");
                }

                //create all possible lines
                List<Line> linesList = new LinkedList<Line>();
                for (int i = 0; i < pointsList.size(); i++) {
                    Point2D p1 = pointsList.get(i);
                    for (int j = i; j < pointsList.size(); j++) {
                        Point2D p2 = pointsList.get(j);
                        if (i == j)
                            continue;

                        linesList.add(new Line(p1, p2));
                    }
                }

                //check all lines with all points
                Map<Line, Point2D> rightPointsMap = new HashMap<Line, Point2D>();
                for (int i = 0; i < linesList.size(); i++) {
                    Line line = linesList.get(i);
                    for (int j = 0; j < pointsList.size(); j++) {
                        Point2D point = pointsList.get(j);
                        if (!point.equals(line.p1) && !point.equals(line.p2))
                            if (line.isOnStraight(point)) {
                                rightPointsMap.put(line, point);
                            }
                    }
                }

                //save all lines
                Set<Line> linesSet = rightPointsMap.keySet();
                //delete duplicates
                List<Line> tempLinelist = new LinkedList<Line>(linesSet);
                List<Line> linesWithoutDuplicates = new LinkedList<Line>();
                outer:
                for (int i = 0; i < tempLinelist.size(); i++) {
                    Line mainLine = tempLinelist.get(i);
                    for (int j = i; j < tempLinelist.size(); j++) {
                        if (i == j)
                            continue;
                        Line tempLine = tempLinelist.get(j);
                        if (tempLine.isParallel(mainLine) && tempLine.isOnStraight(mainLine.p1))
                            continue outer;

                    }

                    linesWithoutDuplicates.add(mainLine);
                }
                appendText("Найденные прямые:\n");
                for (int i = 0; i < linesWithoutDuplicates.size(); i++) {
                    Line line = linesWithoutDuplicates.get(i);
                    appendText(line + "\n");
                }

                //write to file
                try {
                    Path path = FileSystems.getDefault().getPath(OUTPUT_FILE_NAME).toAbsolutePath();
                    FileWriter writer = new FileWriter(path.toFile());
                    appendText("Вывод прямых в файл: \"" + path + "\n");
                    for (int i = 0; i < linesWithoutDuplicates.size(); i++) {
                        Line line = linesWithoutDuplicates.get(i);
                        writer.write(line + "\r\n");
                    }
                    writer.close();
                } catch (IOException e) {
                    writeExceptionInfo(e);
                    return;
                }
                appendText("Вывод успешно произведен\n");

            }
        }).start();
    }
}