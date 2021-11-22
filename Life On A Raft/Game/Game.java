package Game;

import java.util.*;
import java.io.File;

import ASCII_ART.ASCIIART_setting;
import java.io.IOException;

/**
 * This Game class implements the game interface and logics of the game.
 * 
 * @author Simon Yang
 * @version 1.0
 * @since 2021-11-21
 */
public class Game {

  // ================================================
  // Global variables to the Game class
  // ================================================
  // The different screens to render from a file.
  private static File Idle = ASCIIART_setting.returnFile("/Idle.txt");
  private static File FishOnLine = ASCIIART_setting.returnFile("/FishOnLine.txt");
  private static File IdleFishing = ASCIIART_setting.returnFile("/IdleFishing.txt");

  // Scanner to take in inputs from the player.
  private static Scanner scan = new Scanner(System.in);

  // Player object
  private static Player player = new Player();

  // All the items in the game.
  private static HashMap<String, Item> items = new HashMap<String, Item>(); // ID, item

  // List of all the text colors and font styles
  private static final String ANSI_RESET = "\u001B[0m";
  private static final String ANSI_BLACK = "\u001B[30m";
  private static final String ANSI_RED = "\u001B[31m";
  private static final String ANSI_GREEN = "\u001B[32m";
  private static final String ANSI_YELLOW = "\u001B[33m";
  private static final String ANSI_BLUE = "\u001B[34m";
  private static final String ANSI_PURPLE = "\u001B[35m";
  private static final String ANSI_CYAN = "\u001B[36m";
  private static final String ANSI_WHITE = "\u001B[37m";

  private static final String ANSI_BRIGHT_BLACK = "\u001B[90m";
  private static final String ANSI_BRIGHT_RED = "\u001B[91m";
  private static final String ANSI_BRIGHT_GREEN = "\u001B[92m";
  private static final String ANSI_BRIGHT_YELLOW = "\u001B[93m";
  private static final String ANSI_BRIGHT_BLUE = "\u001B[94m";
  private static final String ANSI_BRIGHT_PURPLE = "\u001B[95m";
  private static final String ANSI_BRIGHT_CYAN = "\u001B[96m";
  private static final String ANSI_BRIGHT_WHITE = "\u001B[97m";

  private static final String setPlainText = "\033[0m";
  private static final String setBoldText = "\033[1m";
  private static final String setItalicText = "\033[3m";

  public Game(String statsFile, String inventoryFile) {
    // Initialize the game
    initItems();
    initStats(statsFile);
    initInventory(inventoryFile);
  }

  public Game() {
    // Initialize the game
    initItems();
    // Init default stats
    initStats();
  }

  /**
   * This method is the main method that runs the game. The method contains code
   * to render the game and do logics operations base on user's inputs
   * 
   * @return Player Returns player for saving files
   * 
   */
  public Player main() {

    // Initialize the current screen to render
    File currentScreen = Idle;

    // introductionStory();

    // Main game loop.
    while (true) {
      clearScreen();

      // Display the current screen
      displayScreen(currentScreen);

      // Get inputs from the player for the next action.
      String inp = scan.nextLine().toUpperCase();

      switch (inp) {
      case "C":
        crafting();
        continue;
      case "I":
        // Open the inventory
        inventory();
        continue;
      case "F":
        // Open the fishing screen
        fishing();
        break;
      case "E":
        // Exit out of the game
        return player;
      }

      // increment the turn and deduct stats from the player to simulate time has
      // pass.
      player.nextTurn();

      // Check for Player's Health
      if (player.getHealth() == 0) {
        System.out.println("Game Over! You have Died!");
        player.gameOver = true;
        wait(1000);
        return player;
      }
    }
  }

  /**
   * This method initialize and parses the defaultStats from a flat file
   * 
   * @return void
   * 
   */
  private void initStats() {
    // initialize the default stats
    try {
      // Scan the stats file into a 2d array for easier access.
      Scanner scan = new Scanner(new File("./Game/defaultStats.txt"));
      String[][] stats = new String[2][1];
      int line = 0;

      while (scan.hasNextLine()) {
        stats[line] = scan.nextLine().split(",");
        line++;
      }
      // Store the stats to player stats
      player.stats.put(stats[0][0], Integer.parseInt(stats[1][0]));
      player.stats.put(stats[0][1], Integer.parseInt(stats[1][1]));
      player.stats.put(stats[0][2], Integer.parseInt(stats[1][2]));

      scan.close();
    } catch (IOException e) {
      System.out.println("Java Exception: " + e);
    }
  }

  /**
   * This method initialize and parses the player's stats and other information
   * from the save file
   * 
   * @param saveFile The name of the save file to be loaded.
   * @return void
   * 
   */
  private void initStats(String saveFile) {
    try {
      // Scan the save file into a 2d array for easier access.
      Scanner scan = new Scanner(new File("./Game/" + saveFile));
      String[][] info = new String[2][7]; // Init only two memory slots because we only have one save file at a time.
      int line = 0;

      while (scan.hasNextLine()) {
        info[line++] = scan.nextLine().split(",");
      }

      // Store the information to player stats
      player.setTurn(Integer.parseInt(info[1][0]));
      player.setCurrentRod(items.get(info[1][1]));
      player.setFishingBuff(Integer.parseInt(info[1][2]));
      player.stats.put(info[0][3], Integer.parseInt(info[1][3]));
      player.stats.put(info[0][4], Integer.parseInt(info[1][4]));
      player.stats.put(info[0][5], Integer.parseInt(info[1][5]));

      scan.close();
    } catch (IOException e) {
      System.out.println("Java Exception: " + e);
    }
  }

  /**
   * This method initialize and parses the inventory from a save file
   * 
   * @param saveFile The name of the save file to be loaded.
   * @return void
   * 
   */
  private void initInventory(String saveFile) {
    try {
      // Scan the save file into a 2d array for easier access.
      Scanner scan = new Scanner(new File("./Game/" + saveFile));
      String[][] info = new String[100][3];
      int line = 0;

      // Save the information into the 2d array.
      while (scan.hasNextLine()) {
        info[line++] = scan.nextLine().split(",");
      }

      // Parse the information and save into player's inventory.
      for (int i = 1; i < info.length; i++) {
        if (info[i][0] == null) {
          break;
        }
        player.inventory.put(items.get(info[i][1]), Integer.parseInt(info[i][2]));
      }
      scan.close();
    } catch (IOException e) {
      System.out.println("Java Exception: " + e);
    }
  }

  /**
   * This method initialize and parses the items from a flat file.
   * 
   * @return void
   * 
   */
  private void initItems() {
    // initialize items into the global item hashMap
    try {
      // Scan the items file and read each item line by line.
      Scanner scan = new Scanner(new File("./Game/Items.txt"));
      int lineN = 0;
      while (scan.hasNextLine()) {
        String line = scan.nextLine();
        String[] info = line.split(",");

        // Catch the labels before its gets saved into the array.
        if (info[0].equals("Name")) {
          continue;
        }

        // Parse all the important information.
        String name = info[0].trim();
        String ID = info[1].trim();
        boolean isUseable = Boolean.parseBoolean(info[2].trim());
        String usedBuff = info[3].trim();
        boolean isCraftable = Boolean.parseBoolean(info[4].trim());

        // Initialize an item object to store all the information about the item.
        Item item = new Item(name, ID, isUseable, isCraftable);

        // Store the effects of the item
        if (usedBuff.equals("NA")) {
        } else if (usedBuff.equals("x2 item")) {
          item.addBuff("itemBuff", 2);
        } else if (usedBuff.equals("x3 item")) {
          item.addBuff("itemBuff", 3);
        } else {
          // Add stats buffs
          String[] inf = usedBuff.split(" ");
          item.addBuff(inf[1], Integer.parseInt(inf[0]));
        }

        // Add recipe to each item
        if (isCraftable) {
          for (int i = 5; i < info.length; i++) {
            String[] recipe = info[i].split(" ");
            item.addRecipe(items.get(recipe[0]), Integer.parseInt(recipe[2]));
          }
        }
        items.put(ID, item);
      }
    } catch (IOException e) {
      System.out.println("Java Exception: " + e);
    }
  }

  /**
   * This method formats the output screen to include colors and status
   * information.
   * 
   * @param file The raw file to be formatted.
   * @return String This returns the new output screen.
   * 
   */
  private static String formateOutput(File file) {

    // Declare local variables for the status colors.
    String healthColor = ANSI_BRIGHT_RED;
    String HungerColor = ANSI_BRIGHT_YELLOW;
    String hydrationColor = ANSI_BRIGHT_BLUE;

    String output = "";

    try {
      // Open the file and scan each line.
      Scanner fileInput = new Scanner(file);

      while (fileInput.hasNextLine()) {
        String line = fileInput.nextLine();

        // Replace each line with its corresponding colors and stats when the line
        // contains the specific information required.

        if (line.contains("Health")) {
          int closingIndex = line.indexOf("}");
          int healthIndex = line.indexOf("Health: {");
          output += line.substring(0, healthIndex) + healthColor + "Health: " + player.stats.get("Health").toString()
              + ANSI_RESET + " " + line.substring(closingIndex + player.stats.get("Health").toString().length());

        } else if (line.contains("Hunger")) {
          int closingIndex = line.indexOf("}");
          int HungerIndex = line.indexOf("Hunger: {");
          output += line.substring(0, HungerIndex) + HungerColor + "Hunger: " + player.stats.get("Hunger").toString()
              + ANSI_RESET + " " + line.substring(closingIndex + player.stats.get("Hunger").toString().length());

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

  /**
   * This method renders the fishing menu and mechanics of fishing.
   * 
   * @return void
   * 
   */
  private static void fishing() {
    clearScreen();
    displayScreen(IdleFishing);

    // Generate a random number between 3-6 seconds to simulate some time has pass.
    int randint = randomInt(3, 6);

    // Display some fishing dialogs
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

    System.out.println("The float is shaking continuously and water is rippling. A bit!");
    displayScreen(FishOnLine);

    // Find fishable items
    Item[] fishableItem = new Item[10];
    int i = 0;
    for (var entry : items.entrySet()) {
      if (!entry.getValue().isCraftable()) {
        fishableItem[i++] = entry.getValue();
      }
    }
    // Get player's input to reel in the item. Randomize the item by indexing the
    // hashmap by a random number.
    int randItemIndex = randomInt(0, fishableItem.length - 1);
    Item item = fishableItem[randItemIndex];

    System.out.println("Enter R to reel it in");
    String inp = scan.nextLine().toUpperCase();

    int rand = randomInt(0, 1); // Maybe change if there is fishing buff??

    // Depending on the input and the random number, the player either gets the
    // item or don't.
    if (inp.equals("R") && rand == 1) {
      wait(1000);
      System.out.println("You are lucky enough to reel up a(n) " + item.getName());

      // Check if the item exists in the inventory
      if (player.inventory.containsKey(item)) {
        player.inventory.replace(item, player.inventory.get(item) + (1 * player.getFishingBuff()));
      } else {
        // Add the item to the inventory if it doesn't already exist'
        player.inventory.put(item, (1 * player.getFishingBuff()));
      }
    } else {
      wait(1000);
      System.out.println("You try reeling it in, but failed. It got away.");
    }

    wait(2000);
  }

  /**
   * This method renders the inventory and a menu to perform some actions to an
   * item.
   * 
   * @return void
   * 
   */
  private static void inventory() {
    // A while loop to constantly display the inventory
    while (true) {
      // Clear the screen
      clearScreen();

      // ================================================
      // Display the inventory screen
      // ================================================
      // The top part of the inventory screen
      for (int i = 0; i < ASCIIART_setting.WIDTH; i++) {
        System.out.print("*");
      }
      System.out.println();

      // All the items in the inventory
      player.inventory.forEach((key, value) -> {
        if (key != null) {
          // Format each item in the inventory
          System.out.printf("* %-25s%-20s x%4d%17s\n", key.getName(), "<" + key.getID() + ">", value, "*");
        }
      });

      // The bottom part of the inventory screen

      for (int i = 0; i < ASCIIART_setting.WIDTH; i++) {
        System.out.print("*");
      }
      System.out.println();

      // ================================================
      // Get inputs
      // ================================================
      // Inventory menu
      System.out.println(setBoldText + setItalicText + "use <ID>" + setPlainText + " to use a item(use fish)");
      System.out.println(setBoldText + setItalicText + "Exit" + setPlainText + " to return to main menu");

      String inp = scan.nextLine().toLowerCase();

      String[] info = inp.split(" ");

      // Check the inputs for the correct inputs
      if (info.length == 1) {
        // The input is Exit
        if (info[0].equals("exit")) {
          break;
        }
      } else if (info.length == 2) {
        // The input is use <ID>

        // Check for the correct input and valid item
        if (info[0].equals("use") && items.containsKey(info[1])) {
          Item item = items.get(info[1]);
          // Check if the item is usable.
          if (items.get(info[1]).isUseable() && player.inventory.containsKey(item)) {
            // Use the item
            player.useItem(items.get(info[1]));

            player.inventory.replace(item, player.inventory.get(item) - 1);
            if (player.inventory.get(item) == 0) {
              player.inventory.remove(item);
            }
          } else {
            System.out.println("Item not useable");
            wait(1000);
          }
        } else {
          System.out.println("Invalid Input!");
          wait(1000);
        }
      } else {
        System.out.println("Invalid Input!");
        wait(1000);
      }
    }
  }

  /**
   * This method renders the crafting menu
   * 
   * @return void
   * 
   */
  private static void crafting() {
    boolean run = true;
    while (true) {
      // Clear the screen
      clearScreen();

      // ================================================
      // Display the Crafting screen
      // ================================================
      // The top part of the Crafting screen
      for (int i = 0; i < ASCIIART_setting.WIDTH; i++) {
        System.out.print("*");
      }
      System.out.println();

      // All the items in the Crafting menu
      items.forEach((name, item) -> {
        boolean craftable = true;
        if (item.isCraftable()) {
          for (var recipe : item.getRecipe().entrySet()) {
            if (!player.inventory.containsKey(recipe.getKey())) {
              craftable = false;
            } else if (player.inventory.get(recipe.getKey()) < recipe.getValue()) {
              craftable = false;
            }
          }
          if (craftable) {
            System.out.print(ANSI_BLUE);
            System.out.printf("* %-25s %-20s %21s\n", item.getName(), "<" + item.getID() + ">", "*");
            System.out.print(ANSI_RESET);
          } else {
            System.out.printf("* %-25s %-20s %21s\n", item.getName(), "<" + item.getID() + ">", "*");
          }
          HashMap<Item, Integer> recipes = item.getRecipe();

          recipes.forEach((recipeItem, quantity) -> {
            System.out.printf("*    -> %-25s x%4d%31s\n", recipeItem.getName(), quantity, "*");
          });
          System.out.printf("*%68s*\n", " ");
        }
      });

      // The bottom part of the Crafting screen
      for (int i = 0; i < ASCIIART_setting.WIDTH; i++) {
        System.out.print("*");
      }
      System.out.println();

      // ================================================
      // Get inputs
      // ================================================
      // Crafting menu
      System.out.println(setBoldText + setItalicText + "Craft <ID>" + setPlainText + " to craft a item(craft planks)");
      System.out.println(setBoldText + setItalicText + "Exit" + setPlainText + " to return to main menu");

      String inp = scan.nextLine().toLowerCase();

      String[] info = inp.split(" ");

      // Check the inputs for the correct inputs
      if (info.length == 1) {
        // The input is Exit
        if (info[0].equals("exit")) {
          break;
        }
      } else if (info.length == 2) {
        // The input is use <ID>

        // Check for the correct input and valid item
        if (info[0].equals("craft") && items.containsKey(info[1])) {

          Item item = items.get(info[1]);
          HashMap<Item, Integer> recipes = item.getRecipe();

          // Check if you have all items in order to craft it.
          for (var recipe : recipes.entrySet()) {
            if (!player.inventory.containsKey(recipe.getKey())) {
              System.out.println("You don't not have enough items to craft!");
            } else if (player.inventory.get(recipe.getKey()) < recipe.getValue()) {
              System.out.println("You don't not have enough items to craft!");
            } else {
              continue;
            }
            run = false;
            wait(1000);
            break;
          }

          // while loop check point
          if (!run)
            continue;

          // Take away the items to confirm the craft.
          recipes.forEach((recipeItem, quantity) -> {
            if (player.inventory.get(recipeItem) - quantity == 0) {
              player.inventory.remove(recipeItem);
            } else {
              player.inventory.replace(recipeItem, player.inventory.get(recipeItem) - quantity);
            }
          });
          if (player.inventory.containsKey(item)) {
            player.inventory.replace(item, player.inventory.get(item) + 1);
          } else {
            player.inventory.put(item, 1);
          }
          wait(1000);

        } else {
          System.out.println("Invalid Input!");
          wait(1000);
        }
      } else {
        System.out.println("Invalid Input!");
        wait(1000);
      }
    }

  }

  /**
   * This method renders the introduction story with a 1s delay per line.
   * 
   * @return void
   * 
   */
  private static void introductionStory() {
    // Clear previous screen
    clearScreen();
    // Read beginning story file.
    File beginningStory = new File("./Game/beginningStory.txt");
    try {
      Scanner fileInput = new Scanner(beginningStory);

      // Read until no more lines are left.
      while (fileInput.hasNextLine()) {
        System.out.println(fileInput.nextLine());
        // Add delay for the player to have read.
        wait(2000);
      }
      scan.nextLine();
      fileInput.close();
    } catch (IOException e) {
      System.err.println("Java Exception: " + e);
    }
  }

  // ================================================
  // Helper methods
  // ================================================

  /**
   * This method renders the screen
   * 
   * @param screen The file to render onto the console
   * @return void
   * 
   */
  private static void displayScreen(File screen) {
    // Pass the file to formate the file and display the returned screen.
    System.out.print(formateOutput(screen));
  }

  /**
   * This method returns a random number between min and max
   * 
   * <p>
   * <b>NOTE: </b>Due to the built-in method, it will always return a random
   * number between 0 and a number. By adding the min value, and altering the
   * parameter value, we can offset the random number by the min value. For
   * example, if you want a random number between 10 and 20 inclusive, we can
   * offset the value by adding the min value to the random value. To account for
   * the addition of the min value, we will subtract the min value from the max
   * value.
   * 
   * @param min The min value inclusive of the random number.
   * @param max The min value inclusive of the random number.
   * @return void
   * 
   */
  private static int randomInt(int min, int max) {
    return new Random().nextInt(max - min + 1) + min;
  }

  /**
   * The method puts the thread into sleep for the specified amount of time
   * 
   * @param ms The amount of time to sleep in milliseconds.
   * @return void
   * 
   */
  private static void wait(int ms) {
    try {
      Thread.sleep(ms);
    } catch (InterruptedException ex) {
      Thread.currentThread().interrupt();
    }
  }

  /**
   * The method Clears the console screen base on the size
   * <p>
   * 
   * <b>Note: </b> Some of the console text may not be clear due the console's
   * size.
   * 
   * @return void
   * 
   */
  private static void clearScreen() {
    // ================================================
    // A method to clear the console
    // ================================================
    System.out.print("\033[H\033[2J");
    System.out.flush();
  }
}