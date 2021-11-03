package Game;

import java.util.*;
import java.io.File;
import ASCII_ART.Loader;
import FileLoad.FileLoader;

public class Game {

  private static String FishOnLine = "FishOnLine";
  private static String Idle = "Idle";
  private static String IdleFishing = "IdleFishing";

  private HashMap<String, Integer> stats = new HashMap<String, Integer>();

  public void main() {
    // Update per Turn
    while (true) {
      String out = this.parseFile(Idle);
      System.out.println(out);
    }
  }

  public String parseFile(String fileName) {
    // Parse the file and access mainly the stats; Health, Hungry, and hydration
    // using the special char '{' and '}'
    File file = Loader.returnFile(fileName);

    // Read file and convert into an array
    String output = FileLoader.readFile(file);
    String[] out = output.split(" ");

    for (int i = 0; i < out.length; i++) {
      if (out[i].equals("Health:")) {
        if (stats.containsKey("Health")) {
          stats.replace("Health", Integer.parseInt(out[i + 1]));
        } else {
          stats.put("Health", Integer.parseInt(out[i + 1]));
        }
      }

      if (out[i].equals("Hungry:")) {
        if (stats.containsKey("Hungry")) {
          stats.replace("Hungry", Integer.parseInt(out[i + 1]));
        } else {
          stats.put("Hungry", Integer.parseInt(out[i + 1]));
        }
      }

      if (out[i].equals("Hydration:")) {
        if (stats.containsKey("Hydration")) {
          stats.replace("Hydration", Integer.parseInt(out[i + 1]));
        } else {
          stats.put("Hydration", Integer.parseInt(out[i + 1]));
        }
      }
    }

    return output;
  }

  public void update() {
  }
}

// Story Introduction
// Draw Screen
// Get input
// Rerender base on input
// Story Ending
// (Win Condition)