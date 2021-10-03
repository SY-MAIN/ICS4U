package Game;

import java.util.Random;

public class Game {
  private int boardSize = 100;
  private String board[] = new String[this.boardSize];

  private Player players[] = new Player[4];

  public Game() {
    this.init();
  }

  private void init() {
    System.out.println("Quack");
    // Init Board
    for (int i = 0; i < this.boardSize; i++) {
      this.board[i] = "_";
    }
    // Init Players
    // Top Left Corner
    this.board[0] = "X";
    this.players[0] = new Player("Player 1");

    // Top right Corner
    this.board[9] = "Y";
    this.players[1] = new Player("Player 2");

    // Bottom Left Corner
    this.board[90] = "W";
    this.players[2] = new Player("Player 3");

    // Bottom Right Corner
    this.board[99] = "X";
    this.players[3] = new Player("Player 4");

    // Randomize
    Random randObj = new Random();
    System.out.println(randObj.nextInt(2) + 1);
  }

  public void displayBoard() {
    int numPerRow = 10;
    for (int i = 0; i < numPerRow; i++) {
      System.out.print(" _");
    }
    System.out.println();
    for (int i = 0; i < this.boardSize / numPerRow; i++) {
      for (int j = 0; j < numPerRow; j++) {
        System.out.print("|");
        System.out.print(this.board[i * numPerRow + j]);
      }
      System.out.println("|");
    }
  }
}
