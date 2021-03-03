import java.util.Map.Entry;

public class Main {


  private static void timeOBS(int keyCount) {
    KeysAndProbabilities keysAndProbabilities = new KeysAndProbabilities(keyCount, keyCount, keyCount);
    keysAndProbabilities.saveKeys();

    System.out.println("Keys Made!");
    Long start = System.currentTimeMillis();
    Long end = System.currentTimeMillis();

    // Run for
    start = System.currentTimeMillis();
    OptimalBST obst = new OptimalBST(keysAndProbabilities.regularKeys, keysAndProbabilities.dummyKeys);
    end = System.currentTimeMillis();
    System.out.println("Time for " + keyCount + " keys was " + (end-start) + "(ms)");


  }


  public static void main(String[] args) {
    timeOBS(10000);

  }
}
