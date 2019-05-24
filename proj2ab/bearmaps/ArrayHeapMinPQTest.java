package bearmaps;

import org.junit.Test;
import static org.junit.Assert.assertEquals;

public class ArrayHeapMinPQTest {
    private static ArrayHeapMinPQ<String> pq;

    @Test
    public static void addTest() {
        pq = new ArrayHeapMinPQ<>();
        pq.add("Ten", 10.);
        pq.add("Eight", 8.);
        pq.add("Five", 5);
        pq.add("Four", 4);
        pq.add("Six", 6);
    }

    public static void removeSmallestTest() {
        pq.removeSmallest();
        pq.removeSmallest();
        pq.removeSmallest();
        pq.removeSmallest();
        pq.removeSmallest();
    }

    @Test
    public static void containsTest() {
        assertEquals(pq.contains("Five"), true);
        assertEquals(pq.contains("Ten"), true);
        assertEquals(pq.contains("Zero"), false);
        assertEquals(pq.contains("Nine"), false);
    }

    public static void getSmallestTest() {
        pq.getSmallest();
        pq.add("One", 1);
        pq.getSmallest();
    }



    public static void main(String[] args) {
        addTest();
        containsTest();
        removeSmallestTest();
    }
}
