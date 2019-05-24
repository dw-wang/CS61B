package bearmaps;
import java.awt.print.PrinterAbortException;
import java.util.ArrayList;
import java.util.NoSuchElementException;
import java.util.HashMap;
import java.util.Collections;

public class ArrayHeapMinPQ<T> implements ExtrinsicMinPQ<T> {

    private ArrayList<PriorityNode> minPQ;
    // Using HashMap to record existing items and their corresponding index in the heap
    private HashMap<T, Integer> map;

    public ArrayHeapMinPQ() {
        minPQ = new ArrayList<PriorityNode>();
        map = new HashMap<T, Integer>();

        // Add an arbitrary item at the zero's entry of the minPQ array
        // so that the children of index k are 2k and 2k+1
        minPQ.add(null);
    }

    @Override
    public void add(T item, double priority) {

        if (contains(item)) {
            throw new IllegalArgumentException("This item already exists!");
        } else {
            // Append item to the end of the array
            minPQ.add(new PriorityNode(item, priority));
            map.put(item, size());
            swim(size());
        }
    }

    @Override
    public boolean contains(T item) {
        return map.containsKey(item);
    }

    @Override
    public T getSmallest() {
        if (size()  == 0) {
            throw new NoSuchElementException("No items exist in the queue!");
        } else {
            return (T) minPQ.get(1).item;
        }
    }

    @Override
    public T removeSmallest() {
        if (size() == 0) {
            throw new NoSuchElementException("No items exist in the queue!");
        } else if (size() == 1) {
            T item = (T) minPQ.remove(1).item;
            map.remove(item);
            return item;
        } else {
            T smallest = getSmallest();
            minPQ.set(1, minPQ.remove(size()));
            map.remove(smallest);
            sink(1);
            return smallest;
        }

    }

    @Override
    public int size() {
        int arrSize = minPQ.size();
        if (arrSize != 0) {
            return arrSize - 1;
        } else {
            return 0;
        }
    }

    @Override
    public void changePriority(T item, double priority) {
        if (!contains(item)) {
            throw new NoSuchElementException("This item does not exist in the queue!");
        } else {
            int item_index = map.get(item);
            double original_priority = minPQ.get(item_index).priority;
            minPQ.get(item_index).priority = priority;    // Change priority of item
            if (priority > original_priority) {
                sink(item_index);    // Change of priority should make this item sink down
            }
            if (priority < original_priority) {
                swim(item_index);    // Change of priority should make this item swim up
            }
        }
    }

    // This function already assumes that k has a parent
    private int parent(int k) {
        return k/2;
    }

    private boolean hasParent(int k) {
        return k > 1;
    }

    private int hasChildren(int k) {
        if (size() < 2*k) {
            return 0;       // Index k does not have any children
        } else if (size() == 2*k) {
            return 1;       // Index k has only 1 child
        } else {
            return 2;       // Index k has two children
        }
    }

    // Swap ArrayList elements at index i and index j
    private void swap(int k, int j) {
        Collections.swap(minPQ, k, j);

        // Reflect indices change in the map
        map.replace((T) minPQ.get(k).item, k);
        map.replace((T) minPQ.get(j).item, j);
    }

    // Bubble-up
    private void swim(int k) {
        if (hasParent(k)) {
            PriorityNode child = minPQ.get(k);
            PriorityNode parent = minPQ.get(parent(k));
            if (child.compareTo(parent) < 0) {
                swap(k, parent(k));
                swim(parent(k));
            }
        }
    }

    // Sink down
    private void sink(int k) {
        if (hasChildren(k) == 1) {
            PriorityNode<T> parent = minPQ.get(k);
            PriorityNode<T> child = minPQ.get(2*k);
            if (parent.compareTo(child) > 0) {
                swap(k, 2*k);
            }
        } else if (hasChildren(k) == 2){
            PriorityNode<T> parent = minPQ.get(k);
            int smaller_child_index = 2*k;
            if (minPQ.get(2*k).compareTo(minPQ.get(2*k+1)) > 0) {
                smaller_child_index = 2*k+1;
            }
            swap(k, smaller_child_index);
            sink(smaller_child_index);
        } else {
            return;
        }
    }

    private class PriorityNode<T> implements Comparable<PriorityNode> {
        public T item;
        public double priority;
        public PriorityNode(T item, double priority){
            this.item = item;
            this.priority = priority;
        }

        @Override
        public int compareTo(PriorityNode o) {
            if (o == null) return -1;
            else return Double.compare(this.priority, o.priority);
        }

        @Override
        @SuppressWarnings("unchecked")
        public boolean equals(Object o) {
            if (o == null || this.getClass() != o.getClass()) return false;
            else return ((PriorityNode) o).item == this.item;
        }

        @Override
        public int hashCode() {
            return item.hashCode();
        }
    }

}
