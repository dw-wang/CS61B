package bearmaps;

import org.junit.Test;

import java.util.Arrays;

import static org.junit.Assert.assertEquals;

public class KDTreeTest {
    public static void kdtreeTest() {
        Point p1 = new Point(1.1, 2.2);
        Point p2 = new Point(3.3, 4.4);
        Point p3 = new Point(-2.9, 4.2);

        KDTree kdtree = new KDTree(Arrays.asList(p1, p2, p3));
        Point nearest = kdtree.nearest(3.0, 4.0);
        System.out.println(nearest.toString());
    }

    public static void main(String[] arg) {
        kdtreeTest();
    }
}
