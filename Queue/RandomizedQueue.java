import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Created by vsubbar on 2/6/2015.
 */
public class RandomizedQueue<Item> implements Iterable<Item> {
    private Item[] items;
    private int qSize;
    private int last = -1;

    public RandomizedQueue() {
        items = (Item[]) new Object[1];
    }

    private void resize(int capacity) {
        Item[] copy = (Item[]) new Object[capacity];
        for (int i = 0; i < qSize; i++) {
            copy[i] = items[i];
        }
        items = copy;
        copy = null;
    }

    public boolean isEmpty() {
        return qSize == 0;
    }

    public int size() {
        return qSize;
    }

    public void enqueue(Item item) {
        if (item == null)
            throw new NullPointerException("Cannot add a null item");

        if (last == items.length - 1) {
            resize(2 * items.length);
        }
        items[++last] = item;
        qSize++;
    }

    public Item dequeue() {
        if (qSize == 0)
            throw new NoSuchElementException("Queue is empty");

        Item item;
        int index = StdRandom.uniform(qSize);
        qSize--;
        item = items[index];
        items[index] = items[last];
        items[last--] = null;
        if (qSize > 0 && qSize == items.length/4)
            resize(items.length / 2);
        return item;
    }

    public Item sample() {
        if (qSize == 0)
            throw new NoSuchElementException("Queue is empty");

        int index = StdRandom.uniform(qSize);
        return items[index];
    }

    public Iterator<Item> iterator() {
        return new RandomizedQueueIterator();
    }

    private class RandomizedQueueIterator implements Iterator<Item> {
        private Item[] iterArray;
        private int current;

        RandomizedQueueIterator() {
            if (qSize > 0) {
                iterArray = (Item[]) new Object[qSize];
                for (int i = 0; i < qSize; i++) {
                    iterArray[i] = items[i];
                }
                StdRandom.shuffle(iterArray);
                current = 0;
            } else {
                current = -1;
            }
        }

        public boolean hasNext() {
            if (qSize == 0 || current == qSize)
                return false;

            return true;
        }

        public Item next() {
            if (qSize == 0 || current == qSize)
                throw new NoSuchElementException("There are no elements to return");
            return iterArray[current++];
        }

        public void remove() {
            throw new UnsupportedOperationException("method remove() not supported");
        }
    }

    public static void main(String[] args) {
        int N = 10;
        int x;
        RandomizedQueue<String> rQ = new RandomizedQueue<String>();

        /*for (int i = 0; i < 3000; i++) {
            rQ.enqueue("A");
            rQ.enqueue("B");
            rQ.enqueue("C");
            while (true) {
                String s = rQ.dequeue();
                if (s == "A")
                    break;
            }
        }*/

        /*Iterator<Integer> iter1 = rQ.iterator();
        Iterator<Integer> iter2 = rQ.iterator();

        for (int i = 0; i < 10; i++) {
            StdOut.print(iter1.next() + " " + iter2.next());
            StdOut.println();
        }*/
    }
}
