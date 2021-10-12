package Game;

import java.util.*;

public class Game {
  // ================================================
  // Global variables
  // ================================================
  // Amount of rows or columns in the board.
  int numPerRow = 10;
  // A 2d array containing all the characters in the board.
  char board[][] = new char[this.numPerRow][this.numPerRow];

  // An array containing all the players.
  Player players[] = new Player[4];

  // font colors
  public static final String ANSI_RED = "\u001B[31m";
  public static final String ANSI_YELLOW = "\u001B[33m";
  public static final String ANSI_PURPLE = "\u001B[35m";
  public static final String ANSI_CYAN = "\u001B[36m";
  public static final String ANSI_WHITE = "\u001B[37m";

  // Class constructor
  public Game(int amountSaved) {
    // initialize the game object
    this.init(amountSaved);
  }

  private void init(int amountSaved) {
    // ================================================
    // Initialize the game object
    // ================================================

    // initialize the board by filling in all the space with ann underscore.
    for (int i = 0; i < this.numPerRow; i++) {
      for (int j = 0; j < this.numPerRow; j++) {
        this.board[i][j] = '_';
      }
    }

    // Init Players by hard coding the rows and col of the player's position
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

    // Randomize Mutation thats is not the user. This mutation is to add more
    // flavour to the game, by making one player more rich or have higher mutation
    // rate.
    // From index array of 1 to 3.
    int randInt = randomInt(1, 3);

    players[randInt].StatusChange();

    // Modify the user's mutationRate base on their donation
    players[0].donationModification(amountSaved);

  }

  public void run() {
    // ================================================
    // Main game method
    // ================================================

    // initialize local variables
    // keep track of each players time.
    long playerTime[] = new long[4];
    boolean running = true;

    // main game loop
    while (running) {

      // Getting the current date
      // This method returns the time in millis
      Date date = new Date();
      long currentTime = date.getTime();

      // Loop through all the players. By taking all the players mutationRate and
      // their time, we calculate how long before they expand to different territory.
      for (int i = 0; i < players.length; i++) {
        Player player = players[i];
        // Check if the player's time is less than the current time, which means it is
        // time for the player to expand to different territory.
        if (playerTime[i] <= currentTime) {
          // Expand to different territory.
          expand(player);

          // Calculate new time
          long time = player.calTime() + currentTime;

          // Save the time for later expansion.
          playerTime[i] = time;
        }
      }

      // Calculate to check if all the territory have been expanded. If all territory
      // have been expanded, the game will end.
      int s = 0;

      // Loop through the players
      for (int i = 0; i < players.length; i++) {
        // Add the player's territory to the total sum.
        s += players[i].numOfTerritory;
      }
      // Check if the total sum is equal to the board size.
      if (s >= this.numPerRow * this.numPerRow) {
        running = false;
      }

      // Display the board.
      this.displayBoard();
      // Delay the time for the user to catch up and see the board.
      wait(1000);
      // Clear the screen
      clearScreen();
    }

    // Calculate which player has the highest number of territory and also figure
    // out which place did the user placed.
    int maxTerritory = players[0].numOfTerritory;
    int podium = 1;

    // Loop through the players
    for (int i = 1; i < players.length; i++) {
      Player player = players[i];

      // Check if the current player is greater than the highest number of territory.
      if (player.numOfTerritory > maxTerritory) {
        // Increment the podium due the user not having the highest number of territory.
        podium++;
        // Set the maximum number of territory.
        maxTerritory = player.numOfTerritory;
      }
    }

    // Display the statistics about the game result.
    System.out.printf("You have taken over %d territory. \n", players[0].numOfTerritory);

    // Have a switch statement to display the podium result.
    switch (podium) {
      case 1:
        System.out.printf("You have placed %dst.\n", podium);
        break;
      case 2:
        System.out.printf("You have placed %dnd.\n", podium);
        break;
      case 3:
        System.out.printf("You have placed %drd.\n", podium);
        break;
    }
  }

  public void displayBoard() {
    // ================================================
    // A method to display the board in a pretty fashion.
    // ================================================

    // Top section of the board
    for (int i = 0; i < this.numPerRow; i++) {
      System.out.print(" _"); // Enclose the top of the board with underscores.
    }

    System.out.println();
    // Have a nested for loop to loop through all the characters in the board.
    for (int i = 0; i < this.numPerRow; i++) {
      for (int j = 0; j < this.numPerRow; j++) {

        // Display walls for each box.
        System.out.print("|");

        // Determine which characters and display according to a color.
        if (board[i][j] == 'X') {
          System.out.print(ANSI_RED + this.board[i][j] + ANSI_WHITE);
        } else if (board[i][j] == 'Y') {
          System.out.print(ANSI_CYAN + this.board[i][j] + ANSI_WHITE);
        } else if (board[i][j] == 'Z') {
          System.out.print(ANSI_PURPLE + this.board[i][j] + ANSI_WHITE);
        } else if (board[i][j] == 'W') {
          System.out.print(ANSI_YELLOW + this.board[i][j] + ANSI_WHITE);
        } else {
          System.out.print(ANSI_WHITE + this.board[i][j]);
        }
      }

      // Display the closing of the box.
      System.out.println("|");
    }
  }

  public void expand(Player player) {
    // ================================================
    // A method to expand the player's territory
    // ================================================

    // Randomize which territory to expand on.
    int randInt = randomInt(0, player.numOfTerritory);
    int numVisited = 0;

    // Loop through the entire board and find the corresponding id
    // Then check whether up, right, bottom, left is a blank space.
    for (int i = 0; i < this.numPerRow; i++) {
      for (int j = 0; j < this.numPerRow; j++) {
        char currentId = this.board[i][j];

        // All the possible direction it can expand to.
        int checkPos[][] = { { -1, 0 }, { 0, -1 }, { 0, 1 }, { 1, 0 } };

        // Check if the currentId is the player Id. Then expand by checking all the
        // boundaries and making sure its not index of out range.
        if (currentId == player.ID) {
          // Increment an counter to match the random number. If its match, the program
          // will expand on that character.
          if (numVisited != randInt) {
            numVisited++;
            continue;
          }

          // Check up, right, bottom, left
          for (int k = 0; k < checkPos.length; k++) {
            // Create new position
            int newY = i + checkPos[k][0];
            int newX = j + checkPos[k][1];
            // Check boundaries
            if (newX < 0 || newX >= this.numPerRow || newY < 0 || newY >= this.numPerRow)
              continue;

            // Check if the new position is a blank space.
            if (this.board[newY][newX] == '_') {
              // Set the new position to the player id.
              this.board[newY][newX] = player.ID;
              // Increment the number of territories.
              player.numOfTerritory++;
              return;
            }
          }
        }
      }
    }
  }

  // ================================================
  // Helper methods
  // ================================================
  public static void clearScreen() {
    // ================================================
    // A method to clear the console
    // ================================================
    System.out.print("\033[H\033[2J");
    System.out.flush();
  }

  public static void wait(int ms) {
    // ================================================
    // A method to wait in milliseconds
    // ================================================
    try {
      Thread.sleep(ms);
    } catch (InterruptedException ex) {
      Thread.currentThread().interrupt();
    }
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
}
