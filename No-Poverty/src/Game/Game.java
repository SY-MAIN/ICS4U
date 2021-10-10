package Game;

import java.util.*;

public class Game {
  int numPerRow = 10;
  char board[][] = new char[this.numPerRow][this.numPerRow];

  Player players[] = new Player[4];

  public Game(int amountSaved) {
    this.init(amountSaved);
  }

  private void init(int amountSaved) {
    // Init Board

    for (int i = 0; i < this.numPerRow; i++) {
      for (int j = 0; j < this.numPerRow; j++) {
        this.board[i][j] = '_';
      }
    }

    // Init Players
    // Top Left Corner
    this.board[0][0] = 'X';
    this.players[0] = new Player("Player 1", 'X');

    // Top right Corner
    this.board[0][this.numPerRow - 1] = 'Y';
    this.players[1] = new Player("Player 2", 'Y');

    // Bottom Left Corner
    this.board[this.numPerRow - 1][0] = 'W';
    this.players[2] = new Player("Player 3", 'W');

    // Bottom Right Corner
    this.board[this.numPerRow - 1][this.numPerRow - 1] = 'Z';
    this.players[3] = new Player("Player 4", 'Z');

    // Randomize Mutation thats is not the user
    // From index array of 1 to 3.
    int randInt = randomInt(1, 3);

    players[randInt].StatusChange();

    // Modify the user's mutationRate base on their donation
    players[0].donationModification(amountSaved);

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
          System.out.println(player.name + " id: " + player.ID + "(" + player.mutationRate + ")");
          System.out.println("status: " + player.status);
          System.out.println("Time:" + currentTime);
          System.out.println("PlayerTime:" + time);
          System.out.println("");
        }
      }
      int s = 0;
      for (int i = 0; i < players.length; i++) {
        s += players[i].numOfTerritory;
      }
      if (s >= this.numPerRow * this.numPerRow) {
        running = false;
      }

      this.displayBoard();
      wait(1000);
      clearScreen();
    }
  }

  public void displayBoard() {
    // Top section of the board
    for (int i = 0; i < this.numPerRow; i++) {
      System.out.print(" _");
    }
    System.out.println();
    for (int i = 0; i < this.numPerRow; i++) {
      for (int j = 0; j < this.numPerRow; j++) {
        System.out.print("|");
        System.out.print(this.board[i][j]);
      }
      System.out.println("|");
    }
  }

  public void expand(Player player) {
    int randInt = randomInt(0, player.numOfTerritory);
    int numVisited = 0;
    // Loop through the entire board and find the corresponding id
    // Then check whether up, right, bottom, left is an blank space.
    for (int i = 0; i < this.numPerRow; i++) {
      for (int j = 0; j < this.numPerRow; j++) {
        char currentId = this.board[i][j];

        int checkPos[][] = { { -1, 0 }, { 0, -1 }, { 0, 1 }, { 1, 0 } };

        if (currentId == player.ID) {
          if (numVisited != randInt) {
            numVisited++;
            continue;
          }
          // Check up, right, bottom, left
          for (int k = 0; k < checkPos.length; k++) {
            int newY = i + checkPos[k][0];
            int newX = j + checkPos[k][1];
            // Check boundaries

            if (newX < 0 || newX >= this.numPerRow || newY < 0 || newY >= this.numPerRow)
              continue;

            if (this.board[newY][newX] == '_') {
              this.board[newY][newX] = player.ID;
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

  public static int randomInt(int min, int max) {
    Random randObj = new Random();
    return randObj.nextInt(max - min) + min;
  }
}
