import java.io.File;
import java.util.Scanner;

import Game.Game;
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

  public static void main(String[] args) {

    while (true) {

      String option[] = { "(1)New Game", "(2)Continue", "(3)Save", "(4)Exit" };
      int result = menuScreen(option);

      switch (result) {
      case 1:
        // FileLoader.removeFile(new File("SaveFile.txt"));
        /*
         * Delete any save file, Render new game,
         */
        new Game("./SaveFile/player.txt", "./SaveFile/inventory.txt").main();
        continue;
      case 2:
        /*
         * Render save file,
         */
        break;
      case 3:
        /*
         * Save current game objects,
         */
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
    Render.displayCenterText(option, ASCIIART_setting.WIDTH);

    return scan.nextInt();
  }
}