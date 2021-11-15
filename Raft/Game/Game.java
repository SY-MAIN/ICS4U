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

  private static Scanner scan = new Scanner(System.in);
  private static Player player;
  private static HashMap<String, Item> items = new HashMap<String, Item>();

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
      init();

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

  private void init() {
    // initialize the stats
    try {
      Scanner scan = new Scanner(new File("./Game/defaultStats.txt"));
      String[][] stats = new String[2][1];
      int line = 0;
      while (scan.hasNextLine()) {
        stats[line] = scan.nextLine().split(", ");
        line++;
      }
      player.stats.put(stats[0][0], Integer.parseInt(stats[1][0]));
      player.stats.put(stats[0][1], Integer.parseInt(stats[1][1]));
      player.stats.put(stats[0][2], Integer.parseInt(stats[1][2]));

      scan.close();
    } catch (IOException e) {
      System.out.println("Java Exception: " + e);
    }

    // initialize items
    // Parse cartable items
    try {
      Scanner scan = new Scanner(new File("./Game/Items.txt"));
      int lineN = 0;
      while (scan.hasNextLine()) {
        String line = scan.nextLine();
        String[] info = line.split(",");

        // Catch the labels before its gets saved into the array.
        if (info[0].equals("Name")) {
          continue;
        }

        String name = info[0].trim();
        String ID = info[1].trim();
        boolean isUseable = Boolean.parseBoolean(info[2].trim());
        String usedBuff = info[3].trim();
        boolean isCraftable = Boolean.parseBoolean(info[4].trim());

        Item item = new Item(name, ID, isUseable, isCraftable);

        if (usedBuff.equals("NA")) {
        } else if (usedBuff.equals("x2 item")) {
          item.addBuff("itemBuff", 2);
        } else if (usedBuff.equals("x3 item")) {
          item.addBuff("itemBuff", 3);
        } else {
          // Add stats buff
          String[] inf = usedBuff.split(" ");
          item.addBuff(inf[1], Integer.parseInt(inf[0]));
        }

        if (isCraftable) {
          for (int i = 5; i < info.length; i++) {
            String[] recipe = info[i].split(" ");
            item.addRecipe(items.get(recipe[0]), Integer.parseInt(recipe[2]));
          }
        }
        items.put(name, item);
      }
    } catch (IOException e) {
      System.out.println("Java Exception: " + e);
    }
  }

  private static String formateOutput(File file) {
    // Change stats
    // Change colors

    String healthColor = ANSI_BRIGHT_RED;
    String HungerColor = ANSI_BRIGHT_YELLOW;
    String hydrationColor = ANSI_BRIGHT_BLUE;
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
              + ANSI_RESET + line.substring(closingIndex + player.stats.get("Health").toString().length() - 1);
        } else if (line.contains("Hunger")) {
          int closingIndex = line.indexOf("}");
          int HungerIndex = line.indexOf("Hunger: {");
          output += line.substring(0, HungerIndex) + HungerColor + "Hunger: " + player.stats.get("Hunger").toString()
              + ANSI_RESET + line.substring(closingIndex + player.stats.get("Hunger").toString().length() - 1);
        } else if (line.contains("Hydration")) {
          int closingIndex = line.indexOf("}");
          int hydrationIndex = line.indexOf("Hydration: {");
          output += line.substring(0, hydrationIndex) + hydrationColor + "Hydration: "
              + player.stats.get("Hydration").toString() + ANSI_RESET + " "
              + line.substring(closingIndex + player.stats.get("Hydration").toString().length());
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