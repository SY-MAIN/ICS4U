package Game;

import java.util.*;

public class Player {
  private String name;
  private int turn = 0;
  public HashMap<String, Integer> stats = new HashMap<String, Integer>(); // Health, Hunger, Hydration

  private int hungerPerTurn = 20; // Lose 20 hunger for each turn
  private int hydrationTurn = 15; // Gain 25 hydration for each turn
  private int healthPerTurn = 10; // Lose 10 health for each turn that is hungry

  public void nextTurn() {
    this.turn++;

    if (this.stats.get("Hunger") <= 0) {
      this.stats.replace("Health", this.stats.get("Health") - 10);
    } else if (this.stats.get("Hunger") - this.hungerPerTurn > 0) {
      this.stats.replace("Hunger", this.stats.get("Hunger") - this.hungerPerTurn);
    } else if (this.stats.get("Hunger") - this.healthPerTurn <= 0) {
      // Set health to Zero when the new Health is less than zero
      this.stats.replace("Health", 0);
    } else {

    }

    this.stats.replace("Hydration", this.stats.get("Hydration") + this.hydrationTurn);
  }

}
