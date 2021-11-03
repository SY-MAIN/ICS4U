import java.io.File;

import Game.Game;

/*
File Name: Raft 
By: Simon Yang
Date Updated: *see history*
Version: Alpha 0.1.1a
Assignment: My Programming Challenge #2
*/

class Main {

  public static void main(String[] args) {
    String option[] = { "(1)New Game", "(2)Continue", "(3)Save", "(4)Exit" };
    int result = Render.menuScreen(option);

    Game game = new Game();

    switch (result) {
    case 1:
      // FileLoader.removeFile(new File("SaveFile.txt"));
      /*
       * Delete any save file, Render new game,
       */
      game.main();
      break;
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
      return;
    }
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
}