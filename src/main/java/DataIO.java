import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Random;
import java.util.Scanner;

public class DataIO {
  public static void saveArrayToFile(Integer[] values, String name) {
    try {
      File dataDir = new File("data/");
      if(dataDir.mkdir())
        System.out.println("Made data directory");
      File file = new File(dataDir.getPath() + "/" + name + ".txt");
      if (file.createNewFile())
        System.out.println("Made File: " + file.getName());

      FileWriter fileWriter = new FileWriter(file);
      BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);

      bufferedWriter.write(Integer.toString(values.length)); // for line should be the number of ints
      bufferedWriter.newLine();

      for(int value : values) {
        bufferedWriter.write(Integer.toString(value));
        bufferedWriter.newLine();
      }
      bufferedWriter.flush();
      bufferedWriter.close();

    } catch (IOException ex) {
      ex.printStackTrace();
    }

  }

  public static int[] readArrayFromFile(String fileName) {
    int[] values = new int[0];
    try {
      Scanner scanner = new Scanner(new File("data/" + fileName + ".txt"));
      int size = scanner.nextInt();
      values = new int[size];

      for(int i = 0; i < size; i++)
        values[i] = scanner.nextInt();

    } catch (FileNotFoundException e) {
      e.printStackTrace();
    }

    return values;
  }


}
