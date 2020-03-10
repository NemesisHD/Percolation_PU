/* *****************************************************************************
 *  Name:Prithvi S Rao
 *  Date:30/5/2019
 *  Description:Deque program
 **************************************************************************** */

import java.util.Iterator;
import java.util.NoSuchElementException;

public class Deque<Item> implements Iterable<Item> {
    private Node first;
    private Node last;
    private int count;

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

    public Deque() {
        first = null;
        last = null;
        count = 0;

    }

    public boolean isEmpty() {
        if (count == 0) {
            return true;
        }
        return false;
    }

    public int size() {
        return count;
    }

    public void addFirst(Item input) {
        if (input == null) {
            throw new IllegalArgumentException();
        }

        if (isEmpty()) {
            first = new Node(input);
            last = first;
        }
        else {
            Node temp = new Node(input);
            temp.next = first;
            first.prev = temp;
            first = temp;
        }
        count++;
    }

    public void addLast(Item input) {
        if (input == null) {
            throw new IllegalArgumentException();
        }
        if (isEmpty()) {
            last = new Node(input);
            first = last;
        }
        else {
            Node temp = new Node(input);
            temp.prev = last;
            last.next = temp;
            last = temp;
        }
        count++;
    }

    public Item removeFirst() {
        if (this.isEmpty()) {
            throw new NoSuchElementException();
        }
        Node temp = first;
        if (size() == 1) {
            first = null;
            last = null;
        }
        else {
            first = first.next;
            first.prev = null;
        }
        if (size() == 0) {
            last = null;
        }
        count--;
        return temp.value;
    }

    public Item removeLast() {
        if (this.isEmpty()) {
            throw new NoSuchElementException();
        }
        Node temp = last;
        if (size() == 1) {
            first = null;
            last = null;
        }
        else {
            last = last.prev;
            last.next = null;
        }
        if (size() == 0) {
            first = null;
        }
        count--;
        return temp.value;
    }

    public Iterator<Item> iterator() {
        return new DequeIterator();
    }

    private class DequeIterator implements Iterator<Item> {
        private Node current = first;

        public boolean hasNext() {
            return current != null;
        }

        public Item next() {
            if (!this.hasNext()) {
                throw new NoSuchElementException();
            }
            Item store = current.value;
            current = current.next;
            return store;
        }

        public void remove() {
            throw new UnsupportedOperationException();
        }
    }
}




