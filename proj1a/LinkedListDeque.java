public class LinkedListDeque<T> {
    private int size;
    private LinkedListNode sentinel;

    private static class LinkedListNode<T> {
        public T item;
        public LinkedListNode previous;
        public LinkedListNode next;

        public LinkedListNode() {
            previous = this;
            next = this;
        }

        public LinkedListNode(T content, LinkedListNode<T> p, LinkedListNode<T> n) {
            item = content;
            previous = p;
            next = n;
        }
    }

    /* Constructor */
    public LinkedListDeque() {
        size = 0;

        LinkedListNode<T> sentNode = new LinkedListNode<>();
        sentinel = sentNode;
    }

    public void addFirst(T item) {
        LinkedListNode<T> firstNode = new LinkedListNode<>(item, sentinel, sentinel.next);
        sentinel.next.previous = firstNode;
        sentinel.next = firstNode;
        size += 1;
    }
    public void addLast(T item) {
        LinkedListNode<T> lastNode = new LinkedListNode<>(item, sentinel.previous, sentinel);
        sentinel.previous.next = lastNode;
        sentinel.previous = lastNode;
        size += 1;
    }

    public boolean isEmpty() {
        if (size == 0) return true;
        else return false;
    }

    public int size() {
        return size;
    }

    public void printFirst() {
        System.out.println(sentinel.next.item);
    }

    public void removeFirst() {
        if (!this.isEmpty()) {
            sentinel.next.next.previous = sentinel;
            sentinel.next = sentinel.next.next;
            size -= 1;
        }
    }

    public void removeLast() {
        sentinel.previous.previous.next = sentinel;
        sentinel.previous = sentinel.previous.previous;
        size -= 1;
    }

    public T get(int index) {
        LinkedListNode<T> currentNode = sentinel.next;
        for (int i = 0; i < index; i++) {
            currentNode = currentNode.next;
        }
        return currentNode.item;
    }

    public static void main(String[] args) {
        LinkedListDeque<Integer> a = new LinkedListDeque<>();
        a.addFirst(5);
        a.addFirst(4);
        a.addFirst(3);
        a.addLast(2);
        a.addLast(1);
        a.removeFirst();
        System.out.println(a.get(0));
        System.out.println(a.get(1));
        System.out.println(a.get(2));
    }


}
