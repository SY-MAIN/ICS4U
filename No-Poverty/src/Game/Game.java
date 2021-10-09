package Game;

import java.util.Date;
import java.util.*;

public class Game {
  private int boardSize = 100;
  private int numPerRow = 10;
  private char board[] = new char[this.boardSize];

  private Player players[] = new Player[4];

  public Game() {
    this.init();
  }

  private void init() {
    System.out.println("Quack");
    // Init Board
    for (int i = 0; i < this.boardSize; i++) {
      this.board[i] = '_';
    }

    // Init Players
    // Top Left Corner
    this.board[0] = 'X';
    this.players[0] = new Player("Player 1", 'X');

    // Top right Corner
    this.board[9] = 'Y';
    this.players[1] = new Player("Player 2", 'Y');

    // Bottom Left Corner
    this.board[90] = 'W';
    this.players[2] = new Player("Player 3", 'W');

    // Bottom Right Corner
    this.board[99] = 'Z';
    this.players[3] = new Player("Player 4", 'Z');

    // Randomize Mutation thats is not the user
    // From index array of 1 to 3.
    Random randObj = new Random();
    int randInt = randObj.nextInt(2) + 1;

    players[randInt].StatusChange();

  }

  public void run() {
    long playerTime[] = new long[4];
    boolean running = true;
    while (running) {
      // Getting the current date
      // This method returns the time in millis
      Date date = new Date();
      long currentTime = date.getTime();

      for (int i = 0; i < players.length; i++) {
        Player player = players[i];
        if (playerTime[i] <= currentTime) {
          expand(player);
          long time = player.calTime() + currentTime;
          playerTime[i] = time;
        }
      }
      this.displayBoard();
      wait(500);
      clearScreen();
    }
  }

  public void displayBoard() {
    // Top section of the board
    for (int i = 0; i < this.numPerRow; i++) {
      System.out.print(" _");
    }
    System.out.println();
    for (int i = 0; i < this.boardSize / this.numPerRow; i++) {
      for (int j = 0; j < this.numPerRow; j++) {
        System.out.print("|");
        System.out.print(this.board[i * this.numPerRow + j]);
      }
      System.out.println("|");
    }
  }

  public void expand(Player player) {

    // Loop through the entire board and find the corresponding id
    // Then check whether up, right, bottom, left is an blank space.

    for (int i = 0; i < this.boardSize / this.numPerRow; i++) {
      for (int j = 0; j < this.numPerRow; j++) {
        int currentPosIdx = i * this.numPerRow + j;
        char currentId = this.board[currentPosIdx];

        // int adjacentPosIdx[] = { i * this.numPerRow + j + 1, i * this.numPerRow + j -
        // 1,
        // i * this.numPerRow + j + this.numPerRow, i * this.numPerRow + j -
        // this.numPerRow };

        // if (currentId == player.getId()) {
        // // Check up, right, bottom, left
        // for (int k = 0; k < adjacentPosIdx.length; k++) {
        // // Check boundaries
        // if (adjacentPosIdx[k] >= 0 && adjacentPosIdx[k] < this.boardSize) {
        // if (this.board[adjacentPosIdx[k]] == '_') {
        // this.board[adjacentPosIdx[k]] = player.getId();
        // player.numOfTerritory++;
        // return;
        // }
        // }
        // }
        // }

        if (currentId == player.getId()) {
          // North
          int current = currentPosIdx - this.numPerRow;
          if (current >= 0) {
            if (this.board[current] == '_') {
              this.board[current] = player.getId();
              player.numOfTerritory++;
              return;
            }
          }

          // South
          current = currentPosIdx + this.numPerRow;
          if (current < this.boardSize) {
            if (this.board[current] == '_') {
              this.board[current] = player.getId();
              player.numOfTerritory++;
              return;
            }
          }

          // East
          current = currentPosIdx + 1;
          if (current < this.boardSize && current % this.numPerRow == i) {
            if (this.board[current] == '_') {
              this.board[current] = player.getId();
              player.numOfTerritory++;
              return;
            }
          }

          // West
          current = currentPosIdx - 1;
          if (current >= 0 && current % this.numPerRow == i) {
            if (this.board[current] == '_') {
              this.board[current] = player.getId();
              player.numOfTerritory++;
              return;
            }
          }
        }
      }
    }
  }

  public Player getPlayerOne() {
    return players[0];
  }

  public Player getPlayerTwo() {
    return players[1];
  }

  public Player getPlayerThree() {
    return players[2];
  }

  public Player getPlayerFour() {
    return players[3];
  }

  public static void wait(int ms) {
    try {
      Thread.sleep(ms);
    } catch (InterruptedException ex) {
      Thread.currentThread().interrupt();
    }
  }

  public static void clearScreen() {
    System.out.print("\033[H\033[2J");
    System.out.flush();
  }
}
