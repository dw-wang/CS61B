package es.datastructur.synthesizer;
import java.util.Iterator;

//TODO: Make sure to that this class and all of its methods are public
//TODO: Make sure to add the override tag for all overridden methods
//TODO: Make sure to make this class implement BoundedQueue<T>

public class ArrayRingBuffer<T>  implements BoundedQueue<T> {
    /* Index for the next dequeue or peek. */
    private int first;
    /* Index for the next enqueue. */
    private int last;
    /* Variable for the fillCount. */
    private int fillCount;
    /* Array for storing the buffer data. */
    private T[] rb;

    /**
     * Create a new ArrayRingBuffer with the given capacity.
     */
    public ArrayRingBuffer(int capacity) {
        // TODO: Create new array with capacity elements.
        //       first, last, and fillCount should all be set to 0.
        rb = (T[]) new Object[capacity];
        first = 0;
        last = 0;
        fillCount = 0;
    }

    /* Return capacity of the ring buffer */
    @Override
    public int capacity() {
        return rb.length;
    }

    /* Return number of items currently buffer */
    @Override
    public int fillCount() {
        return fillCount;
    }

    /**
     * Adds x to the end of the ring buffer. If there is no room, then
     * throw new RuntimeException("Ring buffer overflow").
     */
    @Override
    public void enqueue(T x) {
        // TODO: Enqueue the item. Don't forget to increase fillCount and update
        //       last.
        if (isFull()) {
            throw new RuntimeException("Ring buffer overflow");
        }
        rb[last] = x;
        if (last == capacity()-1) {
            last = 0;
            fillCount += 1;
        } else {
            last += 1;
            fillCount += 1;
        }
    }

    /**
     * Dequeue oldest item in the ring buffer. If the buffer is empty, then
     * throw new RuntimeException("Ring buffer underflow").
     */
    @Override
    public T dequeue() {
        // TODO: Dequeue the first item. Don't forget to decrease fillCount and
        //       update first.
        if (isEmpty()) {
            throw new RuntimeException("Ring buffer underflow");
        }

        T removed = rb[first];
        if (first != capacity()-1) {
            first += 1;
            fillCount -= 1;
        } else {
            first = 0;
            fillCount -= 1;
        }
        return removed;
    }

    /**
     * Return oldest item, but don't remove it. If the buffer is empty, then
     * throw new RuntimeException("Ring buffer underflow").
     */
    @Override
    public T peek() {
        // TODO: Return the first item. None of your instance variables should
        //       change.
        if (isEmpty()) {
            throw new RuntimeException("Ring buffer underflow");
        }
        return rb[first];
    }


    public Iterator<T> iterator() {
        return new ArrayRingBufferIterator();
    }

    private class ArrayRingBufferIterator implements Iterator<T> {

        private int pos;
        private int count;
        public ArrayRingBufferIterator() {
            pos = first;
            count = 0;
        }

        @Override
        public boolean hasNext() {
            return count < fillCount;
        }

        @Override
        public T next() {
            if (!hasNext()) {
                throw new RuntimeException("Ring buffer underflow");
            }

            T element = rb[pos];

            if (first >  last && pos == capacity() - 1) {
                pos = 0;
            } else {
                pos += 1;
            }

            count += 1;
            return element;
        }
    }

    @Override
    public boolean equals(BoundedQueue<T> other) {
        return false;
    }

    // TODO: When you get to part 4, implement the needed code to support
    //       iteration and equals.

}
    // TODO: Remove all comments that say TODO when you're done.
