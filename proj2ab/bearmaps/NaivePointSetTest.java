package bearmaps;
import java.util.List;
import java.util.Arrays;

public class NaivePointSetTest {

    public static void nearestTest() {
        Point p1 = new Point(1.1, 2.2);
        Point p2 = new Point(3.3, 4.4);
        Point p3 = new Point(-2.9, 4.2);

        NaivePointSet pset = new NaivePointSet(Arrays.asList(p1, p2, p3));
        Point nearest = pset.nearest(3.0, 4.0);
        System.out.println(nearest.toString());
    }

    public static void main(String[] arg) {
        nearestTest();
    }
}
