package es.datastructur.synthesizer;
import java.util.Iterator;

public interface BoundedQueue<T> extends Iterable<T> {
    int capacity();       // Return size of the buffer
    int fillCount();      // Return number of items currently in the buffer
    void enqueue(T x);    // Add item to the end
    T dequeue();          // Delete and return item at the front
    T peek();             // Return but do not delete item at the front
    Iterator<T> iterator();
    boolean equals(BoundedQueue<T> other);

    default public boolean isEmpty() {
        if (fillCount() == 0) return true;
        else return false;
    }

    default public boolean isFull() {
        if (fillCount() == capacity()) return true;
        else return false;
    }
}
