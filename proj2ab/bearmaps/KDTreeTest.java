package bearmaps;

import org.junit.Test;

import java.util.Arrays;

import static org.junit.Assert.assertEquals;

public class KDTreeTest {
    public static void kdtreeTest() {
        Point p1 = new Point(2., 3.);
        Point p2 = new Point(1., 5.);
        Point p3 = new Point(4., 2.);
        Point p4 = new Point(4., 5.);
        Point p5 = new Point(3., 3.);
        Point p6 = new Point(4., 4.);
        Point p7 = new Point(2.1, 7.);

        KDTree kdtree = new KDTree(Arrays.asList(p1, p2, p3, p4, p5, p6, p7));
        Point nearest = kdtree.nearest(0., 7.0);
        System.out.println(nearest.toString());
    }

    public static void main(String[] arg) {
        kdtreeTest();
    }
}
