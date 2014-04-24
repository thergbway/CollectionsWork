package geometry;

public class Line {
    public final Point2D p1;
    public final Point2D p2;

    public Line(Point2D startPoint, Point2D endPoint) {
        p1 = startPoint;
        p2 = endPoint;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("(");
        sb.append(p1.x);
        sb.append(", ");
        sb.append(p1.y);
        sb.append(") --- (");
        sb.append(p2.x);
        sb.append(", ");
        sb.append(p2.y);
        sb.append(")");

        return sb.toString();
    }

    /**
     * Лежит ли точка на прямой, которой принадлежит данный отрезок
     *
     * @param p проверяемая точка
     * @return результат проверки
     */
    public boolean isOnStraight(Point2D p) {
        int x1 = p1.x;
        int y1 = p1.y;
        int x2 = p2.x;
        int y2 = p2.y;

        int x3 = p2.x;
        int y3 = p2.y;
        int x4 = p.x;
        int y4 = p.y;

        //проверим, не параллельны ли линии
        boolean isParallel = (x2 - x1) * (y4 - y3) == (y2 - y1) * (x4 - x3);
        if (isParallel)
            return true;
        else
            return false;
    }

    public boolean isParallel(Line l) {
        int x1 = p1.x;
        int y1 = p1.y;
        int x2 = p2.x;
        int y2 = p2.y;
        int x3 = l.p1.x;
        int y3 = l.p1.y;
        int x4 = l.p2.x;
        int y4 = l.p2.y;
        return (x2 - x1) * (y4 - y3) == (y2 - y1) * (x4 - x3);
    }
}