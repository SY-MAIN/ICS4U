
/*
File Name: Raft 
By: Simon Yang
Date Updated: *see history*
Version: Alpha 0.1.1a
Assignment: My Programming Challenge #2
*/
import java.io.File;
import java.util.Scanner;
import java.io.IOException;
import java.io.PrintWriter;

import Game.Game;
import Game.Player;
import ASCII_ART.ASCIIART_setting;

/**
 * The Main class is the main class of the program.
 * 
 * @author Simon Yang
 * @version 1.0
 * @since 2021-11-21
 */
class Main {

  private static Scanner scan = new Scanner(System.in);
  public static Player player = null;

  /**
   * This method is the main method that runs the game. The method contains code
   * that saves and load game files.
   * 
   * @param args Not in use
   * @return void
   * 
   */
  public static void main(String[] args) {
    Player player = new Player();

    System.out.println("Please Play In Full Screen! :)");
    scan.nextLine();

    // Main while loop.
    while (true) {
      // Clear the screen.
      clearScreen();

      // Display the starting menu. Depending on the user's choices, it will perform
      // certain action.
      String option[] = { "(1)New Game", "(2)Continue", "(3)Save", "(4)Exit" };
      int result = menuScreen(option);

      // A switch statement to control the user's choices
      switch (result) {
      case 1:
        // Delete any save file, Render new game.
        newGame();
        saveGame();
        continue;
      case 2:
        // Render and load save file.
        if (player.gameOver) {
          System.out.println("Cannot save game! Game Over Already!");
        } else {
          continueGame();
        }
        saveGame();
        break;
      case 3:
        // Save current game objects, if the game is not over.
        saveGame();
        break;
      case 4:
        // Save current game objects, exit game
        saveGame();
        break;
      }

      // Exit out of game.
      if (result == 4) {
        break;
      }
    }
    // Close Scanner.
    scan.close();
  }

  /**
   * This method deletes and save files and loads a new game with default stats.
   * 
   * @return void
   * 
   */
  public static void newGame() {
    // Check if there is a save file.
    String saveFile = "./Game/SaveFile";

    File inventory = new File(saveFile + "/inventory.txt");
    File playerF = new File(saveFile + "/player.txt");

    // If Save Files exist, delete it.
    if (inventory.exists() && playerF.exists()) {
      System.out.println("Are you sure you want to overwrite your save file?(y/n)");
      String inp = scan.nextLine().toLowerCase();
      if (!inp.startsWith("y")) {
        return;
      }
      // Delete old save files
      inventory.delete();
      playerF.delete();
    }
    // Render new game
    player = new Game().main();

  }

  /**
   * This method loads the save file and renders the game using the save files
   * 
   * @return void
   * 
   */
  public static void continueGame() {
    // Check if there is a save file.
    String saveFile = "./Game/SaveFile";

    File inventory = new File(saveFile + "/inventory.txt");
    File playerF = new File(saveFile + "/player.txt");

    // If the save files exists, then render the game.
    if (inventory.exists() && playerF.exists()) {
      player = new Game("./SaveFile/player.txt", "./SaveFile/inventory.txt").main();
    } else {
      System.out.println("Save File does not exist!");
      wait(1000);
    }
  }

  /**
   * This method saves the game using currently rendered game.
   * 
   * @return void
   * 
   */
  public static void saveGame() {
    // Check if the game is currently running.
    if (player == null) {
      System.out.println("No Game File! Cannot save Save!");
      wait(1000);
      return;
    }
    String saveFile = "./Game/SaveFile";

    // Write to Inventory
    // Access the Inventory save file
    try {
      File inventory = new File(saveFile + "/inventory.txt");
      PrintWriter writer = new PrintWriter(inventory);
      // write Labels
      writer.print("Name,ID,quantity\n");
      // Write the inventory and quantity
      for (var item : player.inventory.entrySet()) {
        writer.printf("%s,%s,%d\n", item.getKey().getName(), item.getKey().getID(), item.getValue());
      }
      // Close Save file to prevent data loss.
      writer.close();
    } catch (IOException e) {
      System.err.println("Java Exception: " + e);
    }

    // Write to player
    // Access the player information saved file
    try {
      File playerF = new File(saveFile + "/player.txt");
      PrintWriter writer = new PrintWriter(playerF);
      // write Labels
      writer.print("turn,currentRod,fishingBuff,Health,Hunger,Hydration\n");

      // Write to save file
      if (player.getCurrentRod() == null) {
        writer.printf("%d,%s,%d,%d,%d,%d\n", player.getTurn(), null, player.getFishingBuff(), player.getHealth(),
            player.getHunger(), player.getHydration());
      } else {
        writer.printf("%d,%s,%d,%d,%d,%d\n", player.getTurn(), player.getCurrentRod().getName(),
            player.getFishingBuff(), player.getHealth(), player.getHunger(), player.getHydration());
      }

      // Close Save file to prevent data loss.
      writer.close();
    } catch (IOException e) {
      System.err.println("Java Exception: " + e);
    }
    System.out.println("Successfully saved game!");
    wait(1000);
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
    System.out.print("\033[H\033[2J");
    System.out.flush();
  }

  /**
   * The method centers the given text on the screen. Usually in a list format.
   * 
   * @param items      The items to center on the screen
   * @param totalWidth The total width of the screen to center.
   * @return void
   * 
   */
  public static void displayCenterText(String items[], int totalWidth) {
    System.out.println();

    for (int i = 0; i < items.length; i++) {
      String current = items[i];
      // The remaining space characters left.
      int spaceLeft = totalWidth - current.length();

      int left = spaceLeft / 2;
      int right = left + current.length();

      // Print left amount of spaces
      for (int j = 0; j < left; j++) {
        System.out.print(" ");
      }
      System.out.print(current);

      // Print right amount of spaces
      for (int j = 0; j < right; j++) {
        System.out.print(" ");
      }

      System.out.println("\n");
    }
  }

  /**
   * The method displays the menu and gets inputs from user.
   * 
   * @param option All the available options in the menu
   * @return void
   * 
   */
  public static int menuScreen(String option[]) {
    // Center the menu
    displayCenterText(option, ASCIIART_setting.WIDTH);

    // Get input from the user.
    while (true) {
      String inp = scan.nextLine().toLowerCase();
      if (inp.equals("new game")) {
        return 1;
      } else if (inp.equals("continue")) {
        return 2;
      } else if (inp.equals("save")) {
        return 3;
      } else if (inp.equals("exit")) {
        return 4;
      }
      // Check if the user entered a number instead.
      try {
        int input = Integer.parseInt(inp);
        return input;
      } catch (Exception e) {
        System.out.println("Invalid Inputs");
      }
    }
  }
}