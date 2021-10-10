package Game;

import java.util.*;

public class Player {
  String name = "";
  char ID;
  String status = "Poor";
  int numOfTerritory = 1;
  double mutationRate; // 0.2 territory per second or 1 territory per 5 second

  public Player(String name, char ID) {
    this.name = name;
    this.ID = ID;
    init();
  }

  private void init() {
    // Randomize the mutationRate between 0.1 and 0.6
    Random randObj = new Random();
    // increment by 0.1 because randobj choses a random number between 0 and 0.5. If
    // by any chance it choses 0, it would mean mutationRate would be 0.
    this.mutationRate = randObj.nextDouble(0.5) + 0.1;
  }

  public void StatusChange() {
    this.status = "rich";
    this.numOfTerritory = 5;
    // Randomize the mutationRate between 0.1 and 0.6
    Random randObj = new Random();
    // increment by 0.3 because randobj choses a random number between 0 and 0.7. If
    // by any chance it choses 0, it would mean mutationRate would be 0.
    this.mutationRate = randObj.nextDouble(0.7) + 0.5;
  }

  public int calTime() {
    // return the mutationRate in milliseconds
    return (int) (1 / this.mutationRate) * 1000;
  }

  public void donationModification(int amountSaved) {
    // Plus 0.1 for every 10 person saved / helped

    mutationRate += (amountSaved / 10) * 0.1;
  }
}
