import java.util.Iterator;

/**
 * Created by vsubbar on 2/6/2015.
 */
public class Subset {
    public static void main(String[] args)
    {
        int k = Integer.parseInt(args[0]);
        if (k == 0)
            return;

        //RandomizedQueue<Character> rQ = new RandomizedQueue<Character>();
        RandomizedQueue<String> rQ = new RandomizedQueue<String>();
        int i, j;

        for (i = 0; i < k; i++) {
            rQ.enqueue(StdIn.readString());
        }

        String s = null;
        for (i = k; !StdIn.isEmpty(); i++) {
            j = StdRandom.uniform(i+1);
            s = StdIn.readString();
            if (j < k) {
                rQ.dequeue();
                rQ.enqueue(s);
            }
        }

        Iterator<String> iter = rQ.iterator();
        while (iter.hasNext())
            StdOut.println(iter.next());

        /*String s = null;
        int m = 0, i = 0, j = 0;
        int[] a = new int[10];
        String[] b = new String[10];

        for (i = 1; i <= 10; i++)
            b[i-1] = args[i];

        for (i = 0; i < 1000; i++) {
            rQ.enqueue(b[0].charAt(0));
            for (j = k; j < 10; j++) {
                m = StdRandom.uniform(j+1);
                if (m < k) {
                    rQ.dequeue();
                    rQ.enqueue(b[j].charAt(0));
                }
            }
            char c = rQ.dequeue();
            a[c - 97]++;
        }*/
    }
}
