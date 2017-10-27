import edu.princeton.cs.algs4.StdRandom;
import java.util.Iterator;
import java.util.*;

public class RandomizedQueue<Item> implements Iterable<Item> {
    private Item[] items;
    private int N;

    public RandomizedQueue() {
        items = (Item[]) new Object[1];
        N = 0;
    }

    public int size() {
        return N;
    }

    public boolean isEmpty() {
        return size() == 0;
    }

    public void enqueue(Item item) {
        if (item == null)
            throw new IllegalArgumentException("null argument on enqueue");

        if (N == items.length)
            items = resize(2 * items.length);
        items[N++] = item;
    }

    public Item dequeue() {
        if (N == 0)
            throw new NoSuchElementException();

        if (N > 0 && N <= items.length / 4)
            resize(items.length / 2);

        int i = StdRandom.uniform(N);
        N -= 1;
        Item response = items[i];
        items[i] = items[N];
        items[N] = null;
        return response;
    }

    public Item sample() {
        if (N == 0)
            throw new NoSuchElementException();
        return items[StdRandom.uniform(N)];
    }

    private Item[] resize(int capacity) {
        Item[] copy = (Item[]) new Object[capacity];
        for (int i = 0; i < N; i += 1)
            copy[i] = items[i];
        return copy;
    }

    public Iterator<Item> iterator() {
        return new RandomizedQueueIterator();
    }

    private class RandomizedQueueIterator implements Iterator<Item> {
        private Item[] randomItems = resize(N);
        private int i = 0;

        public RandomizedQueueIterator() {
            StdRandom.shuffle(randomItems);
        }

        public boolean hasNext() {
            return i < N;
        }

        public void remove() {
            throw new UnsupportedOperationException();
        }

        public Item next() {
            if (i == randomItems.length)
                throw new NoSuchElementException();
            return randomItems[i++];
        }
    }
}