import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Random;
import java.util.Stack;

public class KeysAndProbabilities {
  HashMap<Integer, Double> regularKeys;
  HashMap<Integer, Double> dummyKeys;


  public KeysAndProbabilities(int count, double variance, int mean) {
    generateKeys(count, variance, mean);
  }

  public HashMap<Integer, Double> getRegularKeys() {
    return regularKeys;
  }

  public HashMap<Integer, Double> getDummyKeys() {
    return dummyKeys;
  }


  private void generateKeys(int count, double variance, int mean) {
    this.regularKeys = new HashMap<>();
    this.dummyKeys = new HashMap<>();
    Random rand = new Random();

    HashMap<Integer, Double> tempKeyMap = new HashMap<>();
    // we are going to generate count*2 + 1 keys and then divide every other key into either
    // dummy key or regular key
    int keyCount = (count*2)+1;
    while (tempKeyMap.size() < keyCount){
      int g = (int) (rand.nextGaussian()*variance + mean);
      if(tempKeyMap.containsKey(g))
        tempKeyMap.put(g, tempKeyMap.get(g) + 1);
      else
        tempKeyMap.put(g, 1.0);
    }

    double totalKeys = tempKeyMap.values().stream().reduce(0.0, Double::sum);

    // calculate probabilities for keys
    for(int k : tempKeyMap.keySet()) {
//      System.out.println("Key = " + k + " Count: " + tempKeyMap.get(k));
      tempKeyMap.put(k, (tempKeyMap.get(k) / totalKeys));
    }

    // sort the keys
    Integer[] sortedKeys = tempKeyMap.keySet().toArray(new Integer[tempKeyMap.size()]);
    Arrays.sort(sortedKeys, Collections.reverseOrder());


    Stack<Integer> stackKeys = new Stack<>();
    for(Integer k : sortedKeys)
      stackKeys.push(k);

    boolean dummyKey = true; // use a boolean to alternate dummy key then regular key
    while(!stackKeys.empty()) {
      Integer k = stackKeys.pop();
      if(dummyKey)
        dummyKeys.put(k, tempKeyMap.get(k));
      else
        regularKeys.put(k, tempKeyMap.get(k));

      dummyKey = !dummyKey;
    }

  }

  public void saveKeys() {
    Integer[] rkey = new Integer[regularKeys.size()];
    regularKeys.keySet().toArray(rkey);
    Integer[] dkey = new Integer[dummyKeys.size()];
    dummyKeys.keySet().toArray(dkey);

    DataIO.saveArrayToFile(rkey, "regularKeys" + rkey.length);
    DataIO.saveArrayToFile(rkey, "dummyKeys" + dkey.length);

  }

}
