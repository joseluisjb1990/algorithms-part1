import java.util.*;

public class Deque<Item> implements Iterable<Item> {
    private Node first;
    private Node last;
    private int size;

    private class Node {
        Item item;
        Node next;
        Node previous;
    }

    public Deque() {
        size = 0;
        first = null;
        last = null;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public int size() {
        return size;
    }

    public void addFirst(Item item) {
        if (item == null)
            throw new IllegalArgumentException();

        Node previousFirst = first;
        first = new Node();
        first.item = item;
        first.next = previousFirst;
        if (previousFirst != null)
            previousFirst.previous = first;

        size += 1;

        if (size == 1)
            last = first;
    }

    public void addLast(Item item) {
        if (item == null)
            throw new IllegalArgumentException();
        Node previousLast = last;
        last = new Node();
        last.item = item;
        if (previousLast != null)
            previousLast.next = last;
        last.previous = previousLast;

        size += 1;
        if (size == 1)
            first = last;
    }

    public Item removeFirst() {
        if (isEmpty())
            throw new NoSuchElementException();
        if (first != null) {
            Node previousFirst = first;
            first = previousFirst.next;
            if (first != null) {
                first.previous = null;
            }
            size -= 1;
            if (size == 0)
                last = null;

            return previousFirst.item;
        } else {
            return null;
        }
    }

    public Item removeLast() {
        if (isEmpty())
            throw new NoSuchElementException();

        if (last != null) {
            Node previousLast = last;
            last = previousLast.previous;
            if (last != null) {
                last.next = null;
            }
            size -= 1;
            if (size == 0)
                first = null;
            return previousLast.item;
        } else {
            return null;
        }
    }

    public Iterator<Item> iterator() {
        return new DequeIterator();
    }

    private class DequeIterator implements Iterator<Item> {
        private Node current = first;

        public boolean hasNext() {
            return current != null;
        }

        public void remove() {
            throw new UnsupportedOperationException();
        }

        public Item next() {
            if (current == null)
                throw new NoSuchElementException();

            Item item = current.item;
            current = current.next;
            return item;
        }
    }

    public static void main(String[] args) {
        Deque<Integer> dint = new Deque<Integer>();
        System.out.println(dint);
    }
}