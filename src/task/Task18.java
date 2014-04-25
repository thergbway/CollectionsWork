package task;

import geometry.Point2D;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;

public class Task18 extends Task {
    private static final String TASK_INFO = "На клетчатой бумаге нарисован круг. Вывести в файл описания всех клеток, " +
            "целиком лежащих внутри круга, в порядке возрастания расстояния от клетки до центра круга. " +
            "Использовать класс PriorityQueue.\n\n\n";
    private static final int CELL_SIZE = 5;
    private static final String OUT_FILE_NAME = "content/task18/out.txt";

    public Task18() {
        super("#18", TASK_INFO);
    }

    @Override
    public void execute() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                appendText("Пожалуйста, введите радиус круга - целое число с центром в (0,0)\n");
                int radius = readInt();
                appendText("Введено: " + radius + "\n");
                int startXCoord = -(0 - radius) % CELL_SIZE + (0 - radius) - CELL_SIZE;
                int startYCoord = startXCoord;
                int cellInLine = (-startXCoord - startXCoord) / CELL_SIZE;

                //generate points
                List<Cell> cellsList = new LinkedList<Cell>();
                for (int i = 0; i < cellInLine; i++) {
                    for (int j = 0; j < cellInLine; j++) {
                        cellsList.add(new Cell(startXCoord + i * CELL_SIZE, startYCoord + j * CELL_SIZE));
                    }
                }

                //find appropriate cells
                PriorityQueue<Cell> apprCellsQueue = new PriorityQueue<Cell>();
                for (int i = 0; i < cellsList.size(); i++) {
                    Cell cell = cellsList.get(i);
                    Point2D centerPoint = new Point2D(0, 0);
                    Point2D leftUpPoint = new Point2D(cell.leftUpX, cell.leftUpY);
                    Point2D rightUpPoint = new Point2D(cell.rightUpX, cell.rightUpY);
                    Point2D rightDownPoint = new Point2D(cell.rightDownX, cell.rightDownY);
                    Point2D leftDownPoint = new Point2D(cell.leftDownX, cell.leftDownY);

                    if (
                            leftUpPoint.lengthToPoint2D(centerPoint) <= radius &&
                                    leftDownPoint.lengthToPoint2D(centerPoint) <= radius &&
                                    rightDownPoint.lengthToPoint2D(centerPoint) <= radius &&
                                    rightUpPoint.lengthToPoint2D(centerPoint) <= radius
                            )
                        apprCellsQueue.add(cell);
                }

                //extract to file and show
                try {
                    Path path = FileSystems.getDefault().getPath(OUT_FILE_NAME).toAbsolutePath();
                    FileWriter writer = new FileWriter(path.toFile());
                    appendText("Вывод клеток в файл: \"" + path);
                    appendText("\n");
                    while (!apprCellsQueue.isEmpty()) {
                        Cell cell = apprCellsQueue.poll();
                        double length = cell.lengthToPoint(new Point2D(0, 0));
                        String str = new String("Клетка с координатой верхнего левого угла: " + cell +
                                ". Расстояние до центра: ");
                        String appStr = String.format("%4.2f", length);
                        String finalString = str + appStr + "\r\n";
                        writer.write(finalString);
                        appendText("Writing: " + finalString);
                    }
                    writer.close();
                } catch (IOException e) {
                    writeExceptionInfo(e);
                    return;
                }
                appendText("Вывод в файл успешно завершён\n");
            }
        }).start();
    }

    /**
     * Класс, представляющий из себя клетку
     */
    private class Cell implements Comparable {
        final int leftUpX;
        final int leftUpY;

        final int leftDownX;
        final int leftDownY;

        final int rightUpX;
        final int rightUpY;
        final int rightDownX;
        final int rightDownY;

        private Cell(int leftUpX, int leftUpY) {
            this.leftUpX = leftUpX;
            this.leftUpY = leftUpY;

            leftDownX = leftUpX;
            leftDownY = leftUpY + CELL_SIZE;

            rightUpX = leftUpX + CELL_SIZE;
            rightUpY = leftUpY;

            rightDownX = leftUpX + CELL_SIZE;
            rightDownY = leftUpY + CELL_SIZE;
        }

        @Override
        public String toString() {
            return new Point2D(leftUpX, leftUpY).toString();
        }

        public double lengthToPoint(Point2D p) {
            double centerX = leftUpX + (double) CELL_SIZE / 2;
            double centerY = leftUpY + (double) CELL_SIZE / 2;

            return Math.sqrt((p.x - centerX) * (p.x - centerX) + (p.y - centerY) * (p.y - centerY));
        }

        @Override
        public int compareTo(Object o) {
            Cell that = (Cell) o;
            double thatLength = that.lengthToPoint(new Point2D(0, 0));
            double length = lengthToPoint(new Point2D(0, 0));

            if (length == thatLength)
                return 0;

            if (length > thatLength)
                return 1;
            else
                return -1;
        }
    }
}