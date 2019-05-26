package bearmaps;

import java.util.List;

public class NaivePointSet implements PointSet {

    private List<Point> points;

    public NaivePointSet(List<Point> points) {
        this.points = points;
    }

    @Override
    public Point nearest(double x, double y) {
        Point best = null;
        double best_distance = Double.MAX_VALUE;

        for (Point p: points) {
            double d = Point.distance(p, new Point(x, y));
            if (d < best_distance) {
                best = p;
                best_distance = d;
            }
        }

        return best;
    }
}
