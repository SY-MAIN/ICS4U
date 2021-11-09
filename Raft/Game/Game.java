package Game;

import java.util.*;
import java.io.File;
import ASCII_ART.Loader;
import FileLoad.FileLoader;
import java.io.IOException;

public class Game {

  private static String FishOnLine = "FishOnLine";
  private static String Idle = "Idle";
  private static String IdleFishing = "IdleFishing";

  private HashMap<String, Integer> stats = new HashMap<String, Integer>();

  public Scanner scan = new Scanner(System.in);

  public static final String ANSI_RESET = "\u001B[0m";
  public static final String ANSI_BLACK = "\u001B[30m";
  public static final String ANSI_RED = "\u001B[31m";
  public static final String ANSI_GREEN = "\u001B[32m";
  public static final String ANSI_YELLOW = "\u001B[33m";
  public static final String ANSI_BLUE = "\u001B[34m";
  public static final String ANSI_PURPLE = "\u001B[35m";
  public static final String ANSI_CYAN = "\u001B[36m";
  public static final String ANSI_WHITE = "\u001B[37m";

  public static final String ANSI_BRIGHT_BLACK = "\u001B[90m";
  public static final String ANSI_BRIGHT_RED = "\u001B[91m";
  public static final String ANSI_BRIGHT_GREEN = "\u001B[92m";
  public static final String ANSI_BRIGHT_YELLOW = "\u001B[93m";
  public static final String ANSI_BRIGHT_BLUE = "\u001B[94m";
  public static final String ANSI_BRIGHT_PURPLE = "\u001B[95m";
  public static final String ANSI_BRIGHT_CYAN = "\u001B[96m";
  public static final String ANSI_BRIGHT_WHITE = "\u001B[97m";

  public void main() {

    String currentScreen = Idle;

    // Update per Turn
    while (true) {
      File out = this.parseFile(currentScreen);
      System.out.println(formateOutput(out));
      String inp = scan.nextLine().toUpperCase();

      switch (inp) {
      case "C":
        break;
      case "I":
        break;
      case "F":
        this.fish();
        break;
      case "E":
        return;
      }
    }
  }

  public File parseFile(String fileName) {
    // Parse the file and access mainly the stats; Health, Hungry, and hydration
    // using the special char '{' and '}'
    File file = Loader.returnFile(fileName);

    // Read file and convert into an array
    String output = FileLoader.readFile(file);
    String[] out = output.split(" ");

    for (int i = 0; i < out.length; i++) {
      if (out[i].equals("Health:")) {
        if (stats.containsKey("Health")) {
          stats.replace("Health", Integer.parseInt(this.formatStat(out[i + 1])));
        } else {
          stats.put("Health", Integer.parseInt(this.formatStat(out[i + 1])));
        }
      }

      if (out[i].equals("Hungry:")) {
        if (stats.containsKey("Hungry")) {
          stats.replace("Hungry", Integer.parseInt(this.formatStat(out[i + 1])));
        } else {
          stats.put("Hungry", Integer.parseInt(this.formatStat(out[i + 1])));
        }
      }

      if (out[i].equals("Hydration:")) {
        if (stats.containsKey("Hydration")) {
          stats.replace("Hydration", Integer.parseInt(this.formatStat(out[i + 1])));
        } else {
          stats.put("Hydration", Integer.parseInt(this.formatStat(out[i + 1])));
        }
      }
    }

    return file;
  }

  public String formateOutput(File file) {
    // Change stats
    // Change colors

    String healthColor = ANSI_BRIGHT_RED;
    String hungryColor = ANSI_RED;
    String hydrationColor = ANSI_BLUE;
    String output = "";

    try {

      Scanner fileInput = new Scanner(file);

      while (fileInput.hasNextLine()) {
        String line = fileInput.nextLine();
        // System.out.println("line " + line);

        if (line.contains("Health")) {
          int closingIndex = line.indexOf("}");
          int healthIndex = line.indexOf("Health: {");
          output += line.substring(0, healthIndex) + healthColor + "Health: " + this.stats.get("Health").toString()
              + ANSI_RESET + "  " + line.substring(closingIndex + 1);
          System.out.println(line.substring(closingIndex + 1));
        } else if (line.contains("Hungry")) {
          int closingIndex = line.indexOf("}");
          int hungryIndex = line.indexOf("Hungry: {");
          output += line.substring(0, hungryIndex) + hungryColor + "Hungry: " + this.stats.get("Hungry").toString()
              + ANSI_RESET + "  " + line.substring(closingIndex + 1);
        } else if (line.contains("Hydration")) {
          int closingIndex = line.indexOf("}");
          int hydrationIndex = line.indexOf("Hydration: {");
          output += line.substring(0, hydrationIndex) + hydrationColor + "Hydration: "
              + this.stats.get("Hydration").toString() + ANSI_RESET + "  " + line.substring(closingIndex + 1);
        } else {
          output += line;
        }
        output += "\n";

      }
      fileInput.close();
    } catch (IOException e) {
      System.err.println("Java Exception: " + e);
    }
    return output;
  }

  public void update() {

  }

  public void fish() {

  }

  private String formatStat(String out) {
    // remove {} from out
    // i.e. {100} -> 100
    String a = out.substring(1, out.length() - 1);
    System.out.println(a);
    return a;
  }
}

// Story Introduction
// Draw Screen
// Get input
// Rerender base on input
// Story Ending
// (Win Condition)