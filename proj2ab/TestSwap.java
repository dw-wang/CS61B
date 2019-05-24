import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

public class TestSwap {

    ArrayList<Node> arr;
    HashMap<Node, Integer> map;

    public TestSwap() {
        arr = new ArrayList<Node>();
        arr.add(null);
        map = new HashMap<Node, Integer>();
    }

    public void add(Node n) {
        arr.add(n);
        map.put(n, arr.size()-1);
    }

    private static class Node {
        private int x;
        private int y;

        public Node(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    public static void main(String[] args) {
        TestSwap t = new TestSwap();
        t.add(new Node(0,0));
        t.add(new Node(1,1));
        t.add(new Node(2,2));
        t.add(new Node(3,3));

        // This does not swap elements at indices 0 and 3 as a0 and a3 are only references
        Node a0 = t.arr.get(0);
        Node a3 = t.arr.get(3);
        Node temp = a0;
        a0 = a3;
        a3 = temp;

        // Use Collections.swap to really swap elements
        Collections.swap(t.arr,1, 4);

        // Now update the map indices after swapping
        t.map.replace(t.arr.get(1), 1);
        t.map.replace(t.arr.get(4), 4);

    }
}
