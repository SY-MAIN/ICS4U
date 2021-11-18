package Game;

import java.util.*;

public class Player {
  private String name;
  private int turn = 0;

  private Item currentRod = null;
  private String fishingBuff = "";

  private int hungerPerTurn = 20; // Lose 20 hunger for each turn
  private int hydrationTurn = 15; // Gain 25 hydration for each turn
  private int healthPerTurn = 10; // Lose 10 health for each turn that is hungry/hydrated

  public HashMap<String, Integer> stats = new HashMap<String, Integer>(); // Health, Hunger, Hydration
  public HashMap<Item, Integer> inventory = new HashMap<Item, Integer>();

  public void nextTurn() {
    this.turn++;

    if (this.stats.get("Hunger") <= 0) {
      this.stats.replace("Health", this.stats.get("Health") - this.healthPerTurn);
    } else if (this.stats.get("Hunger") - this.hungerPerTurn > 0) {
      this.stats.replace("Hunger", this.stats.get("Hunger") - this.hungerPerTurn);
    } else if (this.stats.get("Hunger") - this.hungerPerTurn <= 0) {
      // Prevent stats showing negative stats.
      this.stats.replace("Hunger", 0);
    }

    if (this.stats.get("Hydration") <= 0) {
      this.stats.replace("Health", this.stats.get("Health") - this.healthPerTurn);
    } else if (this.stats.get("Hydration") - this.hydrationTurn > 0) {
      this.stats.replace("Hydration", this.stats.get("Hydration") - this.hydrationTurn);
    } else if (this.stats.get("Hydration") - this.hydrationTurn <= 0) {
      // Prevent stats showing negative stats.
      this.stats.replace("Hydration", 0);
    }

    if (this.stats.get("Health") - this.healthPerTurn <= 0) {
      // Set health to Zero when the new Health is less than zero
      this.stats.replace("Health", 0);
    }
  }
}
