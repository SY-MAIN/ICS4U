import java.io.File;
import java.util.Scanner;
import java.io.IOException;
import java.io.PrintWriter;

import Game.Game;
import Game.Player;
import ASCII_ART.ASCIIART_setting;

/*
File Name: Raft 
By: Simon Yang
Date Updated: *see history*
Version: Alpha 0.1.1a
Assignment: My Programming Challenge #2
*/

class Main {

  private static Scanner scan = new Scanner(System.in);
  public static Player player = null;

  public static void main(String[] args) {

    while (true) {
      clearScreen();
      String option[] = { "(1)New Game", "(2)Continue", "(3)Save", "(4)Exit" };
      int result = menuScreen(option);
      scan.nextLine();
      Player player = new Player();

      switch (result) {
      case 1:
        // FileLoader.removeFile(new File("SaveFile.txt"));
        /*
         * Delete any save file, Render new game,
         */
        newGame();
        continue;
      case 2:
        /*
         * Render save file,
         */
        continueGame();
        break;
      case 3:
        /*
         * Save current game objects,
         */
        saveGame();
        break;
      case 4:
        /*
         * Save current game objects, exit game
         */
        break;
      }

      if (result == 4) {
        break;
      }
    }
    scan.close();
  }

  public static void newGame() {
    // Check if there is a save file.
    String saveFile = "./Game/SaveFile";

    File inventory = new File(saveFile + "/inventory.txt");
    File playerF = new File(saveFile + "/player.txt");

    if (inventory.exists() && playerF.exists()) {
      System.out.println("Are you sure you want to overwrite your save file?(y/n)");
      String inp = scan.nextLine().toLowerCase();
      if (!inp.startsWith("y")) {
        return;
      }
      // Delete old save files
      inventory.delete();
      playerF.delete();

      player = new Game().main();
    } else {
      player = new Game().main();
    }
  }

  public static void continueGame() {
    // Check if there is a save file.
    String saveFile = "./Game/SaveFile";

    File inventory = new File(saveFile + "/inventory.txt");
    File playerF = new File(saveFile + "/player.txt");

    if (inventory.exists() && playerF.exists()) {
      player = new Game("./SaveFile/player.txt", "./SaveFile/inventory.txt").main();
    } else {
      System.out.println("Save File does not exist!");
    }
  }

  public static void saveGame() {
    if (player == null) {
      System.out.println("No Game File! Cannot save Save!");
      wait(1000);
      return;
    }
    String saveFile = "./Game/SaveFile";

    // Write to Inventory
    try {
      File inventory = new File(saveFile + "/inventory.txt");
      PrintWriter writer = new PrintWriter(inventory);
      // Labels
      writer.write("Name,ID,quantity");
      for (var item : player.inventory.entrySet()) {
        writer.printf("%s,%s,%d\n", item.getKey().getName(), item.getKey().getID(), item.getValue());
      }
      writer.close();
    } catch (IOException e) {
      System.err.println("Java Exception: " + e);
    }

    // Write to player
    try {
      File playerF = new File(saveFile + "/player.txt");
      PrintWriter writer = new PrintWriter(playerF);
      // Labels
      writer.write("turn,currentRod,fishingBuff,Health,Hunger,Hydration");

      if (player.getCurrentRod() == null) {
        writer.printf("%d,%s,%d,%d,%d,%d\n", player.getTurn(), null, player.getFishingBuff(), player.getHealth(),
            player.getHunger(), player.getHydration());
      } else {
        writer.printf("%d,%s,%d,%d,%d,%d\n", player.getTurn(), player.getCurrentRod().getName(),
            player.getFishingBuff(), player.getHealth(), player.getHunger(), player.getHydration());
      }

      writer.close();
    } catch (IOException e) {
      System.err.println("Java Exception: " + e);
    }
    System.out.println("Successfully saved game!");
  }

  // ================================================
  // Helper methods
  // ================================================
  public static void clearScreen() {
    System.out.print("\033[H\033[2J");
    System.out.flush();
  }

  public static void wait(int ms) {
    try {
      Thread.sleep(ms);
    } catch (InterruptedException ex) {
      Thread.currentThread().interrupt();
    }
  }

  public static long getTime() {
    return System.currentTimeMillis();
  }

  public static void displayCenterText(String items[], int totalWidth) {
    System.out.println();

    for (int i = 0; i < items.length; i++) {
      String current = items[i];
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

  public static int menuScreen(String option[]) {
    displayCenterText(option, ASCIIART_setting.WIDTH);

    return scan.nextInt();
  }

}