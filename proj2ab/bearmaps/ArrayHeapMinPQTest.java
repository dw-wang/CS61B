package bearmaps;

import org.junit.Test;
import static org.junit.Assert.assertEquals;

public class ArrayHeapMinPQTest<T> {
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
        pq.add("Five", 5);
        pq.add("Four", 4);
        pq.add("Six", 6);
        pq.add("Nine", 9);
        pq.add("Two", 2);
        pq.add("Hello", 0);
    }

    @Test
    public static void containsTest() {
        assertEquals(pq.contains("Five"), true);
        assertEquals(pq.contains("Ten"), true);
        assertEquals(pq.contains("Zero"), false);
        assertEquals(pq.contains("Nine"), false);
    }

    public static void getSmallestTest() {
        System.out.println(pq.getSmallest());
        pq.add("One", 1);
        System.out.println(pq.getSmallest());
    }

    public static void changePriorityTest() {
        pq.changePriority("Five", 6);
        pq.changePriority("Six", 12);
    }



    public static void main(String[] args) {
        addTest();
        containsTest();
        removeSmallestTest();
        changePriorityTest();
    }
}
