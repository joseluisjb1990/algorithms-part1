import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;
import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {
   public static int percolation(int n) {
       return StdRandom.uniform(n);
   }
   public static void main(String[] args) {
       System.out.println(percolation(5));
   }
}
