package Game;

import java.util.*;

public class Player {
  // ================================================
  // Global variables
  // ================================================

  // All the information about the player
  String name = "";
  char ID;
  String status = "Poor";
  int numOfTerritory = 1;
  double mutationRate; // 0.2 territory per second or 1 territory per 5 second

  // Constructor
  public Player(String name, char ID) {
    this.name = name;
    this.ID = ID;
    this.init();
  }

  private void init() {
    // ================================================
    // Initialize the player object
    // ================================================
    // Randomize the mutationRate between 0.1 and 0.6
    Random randObj = new Random();
    // increment by 0.1 because randobj choses a random number between 0 and 0.5. If
    // by any chance it choses 0, it would mean mutationRate would be 0.
    this.mutationRate = randObj.nextDouble(0.5) + 0.1;
  }

  public void StatusChange() {
    // ================================================
    // Change the status of one player. Results in increase of mutation rate.
    // ================================================
    this.status = "rich";
    // Randomize the mutationRate between 0.1 and 0.6
    Random randObj = new Random();
    // increment by 0.3 because randobj choses a random number between 0 and 0.7. If
    // by any chance it choses 0, it would mean mutationRate would be 0.
    this.mutationRate = randObj.nextDouble(0.5) + 0.5;
  }

  public int calTime() {
    // ================================================
    // Calculate the expansion time
    // ================================================
    // return the mutationRate in milliseconds by dividing by 1 second to figure out
    // how much territory per second and multiply by 1000 to get the answer in
    // milliseconds.
    return (int) (1 / this.mutationRate) * 1000;
  }

  public void donationModification(int amountSaved) {
    // ================================================
    // A method to modify the mutation base on the donation of the user.
    // ================================================
    // Plus 0.1 for every 10 person saved / helped

    this.mutationRate += (amountSaved / 10) * 0.1;
  }
}
