package Game;

import java.util.*;
import java.io.File;
import ASCII_ART.Loader;
import FileLoad.FileLoader;
import java.io.IOException;

public class Game {

  private static File Idle = Loader.returnFile("Idle");
  private static File FishOnLine = Loader.returnFile("FishOnLine");
  private static File IdleFishing = Loader.returnFile("IdleFishing");

  public static Scanner scan = new Scanner(System.in);
  public static Player player;

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

    File currentScreen = Idle;

    // Create new player
    player = new Player();

    // Update per Turn
    while (true) {
      clearScreen();
      parseFile(currentScreen);

      displayScreen(currentScreen);
      String inp = scan.nextLine().toUpperCase();

      switch (inp) {
      case "C":
        break;
      case "I":
        break;
      case "F":
        fish();
        break;
      case "E":
        return;
      }
    }
  }

  private static File parseFile(File file) {
    // Parse the file and access mainly the stats; Health, Hunger, and hydration
    // using the special char '{' and '}'

    // Read file and convert into an array
    String output = FileLoader.readFile(file);
    String[] out = output.split(" ");

    for (int i = 0; i < out.length; i++) {
      if (out[i].equals("Health:")) {
        if (player.stats.containsKey("Health")) {
          player.stats.replace("Health", Integer.parseInt(formatStat(out[i + 1])));
        } else {
          player.stats.put("Health", Integer.parseInt(formatStat(out[i + 1])));
        }
      }

      if (out[i].equals("Hunger:")) {
        if (player.stats.containsKey("Hunger")) {
          player.stats.replace("Hunger", Integer.parseInt(formatStat(out[i + 1])));
        } else {
          player.stats.put("Hunger", Integer.parseInt(formatStat(out[i + 1])));
        }
      }

      if (out[i].equals("Hydration:")) {
        if (player.stats.containsKey("Hydration")) {
          player.stats.replace("Hydration", Integer.parseInt(formatStat(out[i + 1])));
        } else {
          player.stats.put("Hydration", Integer.parseInt(formatStat(out[i + 1])));
        }
      }
    }

    return file;
  }

  private static String formateOutput(File file) {
    // Change stats
    // Change colors

    String healthColor = ANSI_BRIGHT_RED;
    String HungerColor = ANSI_RED;
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
          output += line.substring(0, healthIndex) + healthColor + "Health: " + player.stats.get("Health").toString()
              + ANSI_RESET + "  " + line.substring(closingIndex + 1);
        } else if (line.contains("Hunger")) {
          int closingIndex = line.indexOf("}");
          int HungerIndex = line.indexOf("Hunger: {");
          output += line.substring(0, HungerIndex) + HungerColor + "Hunger: " + player.stats.get("Hunger").toString()
              + ANSI_RESET + "  " + line.substring(closingIndex + 1);
        } else if (line.contains("Hydration")) {
          int closingIndex = line.indexOf("}");
          int hydrationIndex = line.indexOf("Hydration: {");
          output += line.substring(0, hydrationIndex) + hydrationColor + "Hydration: "
              + player.stats.get("Hydration").toString() + ANSI_RESET + "  " + line.substring(closingIndex + 1);
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

  private static void fish() {
    // Random number between 3-5 seconds
    int randint = randomInt(3, 6);
    displayScreen(IdleFishing);

    for (int i = 0; i < randint; i++) {
      if (i == 1) {
        System.out.println(
            "You cast the hook into the water with both hands at the same time. Soon only a segment of float is above the water.\n");
      } else if (i == randint - 2) {
        System.out.println(
            "The float is swaying slightly, is it the blowing wind or the tentative fish? Just be patient and wait for a while.\n");
      }
      wait(1000);
    }
    System.out.println("The float is shaking continuously and water is rippling. A fish bit!");
    displayScreen(FishOnLine);

    String item = "Fish";
    System.out.println("Enter R to reel it in");
    scan.nextLine();

    wait(1000);
    System.out.println("You are lucky enough to fish up a " + item);
    wait(2000);
  }

  private static String formatStat(String out) {
    // remove {} from out
    // i.e. {100} -> 100
    String a = out.substring(1, out.length() - 1);
    System.out.println(a);
    return a;
  }

  private static void displayScreen(File screen) {
    System.out.print(formateOutput(screen));
  }

  private static long getTime() {
    return System.currentTimeMillis();
  }

  private static long toMillis(int seconds) {
    return seconds * 1000;
  }

  public static int randomInt(int min, int max) {
    // ================================================
    // A method to generate a random number
    // ================================================
    Random randObj = new Random();
    /*
     * Due to the built-in method, it will always return a random number between 0
     * and a number. By adding the min value, and altering the parameter value, we
     * can offset the random number by the min value. For example, if you want a
     * random number between 10 and 20 inclusive, we can offset the value by adding
     * the min value to the random value. To account for the addition of the min
     * value, we will subtract the min value from the max value.
     */
    return randObj.nextInt(max - min) + min;
  }

  public static void wait(int ms) {
    try {
      Thread.sleep(ms);
    } catch (InterruptedException ex) {
      Thread.currentThread().interrupt();
    }
  }

  public static void clearScreen() {
    // ================================================
    // A method to clear the console
    // ================================================
    System.out.print("\033[H\033[2J");
    System.out.flush();
  }
}

// Story Introduction
// Draw Screen
// Get input
// Rerender base on input
// Story Ending
// (Win Condition)