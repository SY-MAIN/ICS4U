package Game;

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

  public void displayBoard() {
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
    boolean expanded = false;
    // Loop through the entire board and find the corresponding id
    // Then check whether up, right, bottom, left is an blank space.

    for (int i = 0; i < this.boardSize / this.numPerRow; i++) {
      for (int j = 0; j < this.numPerRow; j++) {
        int currentPosIdx = i * this.numPerRow + j;
        char currentId = this.board[currentPosIdx];

        int adjacentPosIdx[] = { i * this.numPerRow + j + 1, i * this.numPerRow + j - 1,
            i * this.numPerRow + j + this.numPerRow, i * this.numPerRow + j - this.numPerRow };

        if (currentId == player.getId() && !expanded) {
          // Check up, right, bottom, left
          for (int k = 0; k < adjacentPosIdx.length; k++) {
            // Check boundaries
            if (adjacentPosIdx[k] >= 0 && adjacentPosIdx[k] < this.boardSize) {
              if (this.board[adjacentPosIdx[k]] == '_') {
                this.board[adjacentPosIdx[k]] = player.getId();
                expanded = true;
                break;
              }
            }
          }
          player.numOfTerritory++;
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
}
