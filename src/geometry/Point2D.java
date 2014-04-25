package geometry;

/**
 * Класс, представляющий точку в 2D пространстве
 */
public class Point2D {
    public final int x;
    public final int y;

    /**
     * Конструктор
     *
     * @param x абсцисса точки
     * @param y ордината точки
     */
    public Point2D(int x, int y) {
        this.x = x;
        this.y = y;
    }

    /**
     * Поиск расстояния до указанной точки
     *
     * @param p вторая точка
     * @return найденное расстояние
     */
    public double lengthToPoint2D(Point2D p) {
        return Math.sqrt((p.x - x) * (p.x - x) + (p.y - y) * (p.y - y));
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("(");
        sb.append(x);
        sb.append(", ");
        sb.append(y);
        sb.append(")");

        return sb.toString();
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null)
            return false;

        if (!(obj instanceof Point2D))
            return false;
        Point2D p = (Point2D) obj;
        if (p.x == x && p.y == y)
            return true;
        else
            return false;
    }

    @Override
    public int hashCode() {
        int result = x;
        result = 31 * result + y;
        return result;
    }
}