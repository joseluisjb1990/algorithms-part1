import edu.princeton.cs.algs4.StdIn;
import java.util.NoSuchElementException;;

public class Permutation {

    public static void main(String[] args) {
        int k = Integer.parseInt(args[0]);
        int i = 0;
        RandomizedQueue<String> randomStrings = new RandomizedQueue<String>();

        String s = StdIn.readString();

        while (s != null) {
            randomStrings.enqueue(s);
            try {
                s = StdIn.readString();
            } catch (NoSuchElementException e) {
                s = null;
            }
        }

        for (String sr : randomStrings) {
            System.out.println(sr);
            i++;
            if (i == k)
                break;
        }
    }
}
