import java.util.HashMap;

public class OptimalBST {
  int[][] root;
  Double[][] e;
  Double[][] w;

  public OptimalBST(HashMap<Integer, Double> p, HashMap<Integer, Double> q) {
    int n = p.size();
    Integer[] rkey = new Integer[p.size()];
    Double[] rProbs = new Double[p.size()];
    p.keySet().toArray(rkey);
    p.values().toArray(rProbs);

    Integer[] dkey = new Integer[q.size()];
    Double[] dProbs = new Double[q.size()];
    q.keySet().toArray(dkey);
    q.values().toArray(dProbs);

    // initialize arrays
    e = new Double[n+1][n];
    w = new Double[n+1][n];
    root = new int[n][n];

    Integer[] keys = new Integer[n];
    p.keySet().toArray(keys);

    for(int i = 1; i < n+1; i++) {
      e[i][i-1] = rProbs[i-1];
      w[i][i-1] = rProbs[i-1];
    }

    System.out.println("Done Initializing");

    for (int l = 1; l < n+1; l++){
      System.out.println("Out Loop percent = " + l);
      for(int i = 1; i < n - l + 1; i++) {
        int j = i + l - 1;
        e[i][j]  = Double.MAX_VALUE;
        w[i][j] = w[i][j-1] + rProbs[j] + dProbs[j];
        for(int r = i; r <= j; r++) {
          Double t = e[i][r-1] + e[r+1][j] + w[i][j];
          if (t < e[i][j]) {
            e[i][j] = t;
            root[i][j] = r;
          }
        }
      }
    }
  }


  public int[][] getRoot() {
    return root;
  }

  public Double[][] getE() {
    return e;
  }
}
