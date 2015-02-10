import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Created by vsubbar on 2/6/2015.
 */

public class Deque<Item> implements Iterable<Item> {
    private Node first = null;
    private Node last = null;
    private int qSize = 0;

    private class Node {
        private Item item;
        private Node next;
        private Node prev;

        public Node() {
            item = null;
            next = null;
            prev = null;
        }

        public Item getItem() {
            return item;
        }

        public void setItem(Item i) {
            item = i;
        }

        public Node getNext() {
            return next;
        }

        public void setNext(Node p) {
            next = p;
        }

        public Node getPrev() {
            return prev;
        }

        public void setPrev(Node p) {
            prev = p;
        }
    }

    public Deque() { }

    public boolean isEmpty()
    {
        return first == null;
    }

    public int size()
    {
        return qSize;
    }

    public void addFirst(Item item)
    {
        if (item == null)
            throw new NullPointerException("Can't add a null item");

        Node oldFirst = first;
        first = new Node();
        first.setNext(oldFirst);
        first.setItem(item);

        if (oldFirst != null)
            oldFirst.setPrev(first);

        qSize++;

        if (qSize == 1)
            last = first;
    }

    public void addLast(Item item)
    {
        if (item == null)
            throw new NullPointerException("Can't add a null item");

        Node oldLast = last;
        last = new Node();
        last.setPrev(oldLast);
        last.setItem(item);

        if (oldLast != null)
            oldLast.setNext(last);

        qSize++;

        if (qSize == 1)
            first = last;
    }

    public Item removeFirst()
    {
        if (isEmpty())
            throw new NoSuchElementException("can't remove from an empty queue");

        Node temp = first;
        first = first.getNext();

        if (first != null)
            first.setPrev(null);

        temp.setNext(null);
        qSize--;

        if (qSize == 0)
            last = first;

        return temp.getItem();
    }

    public Item removeLast()
    {
        if (isEmpty())
            throw new NoSuchElementException("can't remove from an empty queue");

        Node temp = last;
        last = last.getPrev();

        if (last != null)
            last.setNext(null);

        temp.setPrev(null);
        qSize--;

        if (qSize == 0)
            first = last;

        return temp.getItem();
    }

    public Iterator<Item> iterator()
    {
        return new DequeIterator();
    }

    private class DequeIterator implements Iterator<Item>
    {
        private Node current = first;

        public boolean hasNext()
        {
            return current != null;
        }

        public Item next()
        {
            if (current == null)
                throw new NoSuchElementException("There are no items to return");

            Item item = current.getItem();
            current = current.getNext();
            return item;
        }

        public void remove()
        {
            throw new UnsupportedOperationException("method remove() not supported");
        }
    }

    public static void main(String[] args)
    {
        Deque<Integer> dQ = new Deque<Integer>();
        //dQ.removeFirst();
        //dQ.removeLast();

        /*dQ.addFirst(1);
        dQ.addFirst(2);
        dQ.removeLast();
        dQ.removeLast();

        for (int i = 0 ; i < 10 ; i++)
        {
            dQ.addFirst(i);
        }

        Iterator<Integer> iter = dQ.iterator();

        for (int i = 0; i < 10; i++) {
            if (iter.hasNext())
                StdOut.println(iter.next());
        }*/

        Iterator<Integer> iter = dQ.iterator();
        iter.next();
    }
}
