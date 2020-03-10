/* *****************************************************************************
 *  Name:Prithvi S Rao
 *  Date:01/06/2019
 *  Description:Randomized queue
 **************************************************************************** */

import edu.princeton.cs.algs4.StdRandom;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class RandomizedQueue<Item> implements Iterable<Item> {
    private Node first;
    private Node last;
    private int count;

    public RandomizedQueue() {
        first = null;
        last = null;
        count = 0;

    }

    private class Node {
        private Node next;
        private Node prev;
        private Item value;

        Node(Item input) {
            next = null;
            prev = null;
            value = input;
        }
    }

    public boolean isEmpty() {
        return count == 0;
    }

    public int size() {
        return count;
    }

    public void enqueue(Item input) {
        if (input == null) {
            throw new IllegalArgumentException();
        }
        if (size() == 0) {
            first = new Node(input);
            last = first;
        }
        else {
            Node temp = new Node(input);
            temp.prev = last;
            last.next = temp;
            last = temp;
        }
        count++;
    }

    public Item dequeue() {
        if (size() == 0) {
            throw new NoSuchElementException();
        }
        Node temp;
        int rand = StdRandom.uniform(size());
        Node current = first;
        for (int i = 0; i < rand; i++) {
            current = current.next;
        }
        if (size() == 1) {
            temp = current;
            first = null;
            last = null;
            count--;
            return temp.value;
        }
        if (current == first) {
            temp = current;
            first = first.next;
            first.prev = null;
            count--;
            return temp.value;
        }
        else if (current == last) {
            temp = current;
            last = last.prev;
            last.next = null;
            count--;
            return temp.value;
        }
        else {
            temp = current;
            current.prev.next = current.next;
            current.next.prev = current.prev;
            current = null;
            count--;
            return temp.value;
        }

    }

    public Item sample() {
        if (size() == 0) {
            throw new NoSuchElementException();
        }
        int rand = StdRandom.uniform(size());
        Node current = first;
        for (int i = 0; i < rand; i++) {
            current = current.next;
        }
        return current.value;
    }

    public Iterator<Item> iterator() {
        return new RandomIterator();
    }

    private class RandomIterator implements Iterator<Item> {
        public boolean hasNext() {
            return size() != 0;
        }

        public Item next() {
            return dequeue();
        }

        public void remove() {
            throw new UnsupportedOperationException();
        }

    }
}

