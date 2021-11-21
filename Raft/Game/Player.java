package Game;

import java.util.*;

public class Player {
  private String name;
  private int turn = 0;

  private Item currentRod = null;
  private int fishingBuff = 1;

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

  public void useItem(Item item) {
    String[] itemBuff = item.getBuff();
    if (itemBuff.length == 0) {
      // item buff is NA
      return;
    }

    if (itemBuff[0].equals("itemBuff")) {
      this.currentRod = item;
      this.fishingBuff = Integer.parseInt(itemBuff[1]);
    } else if (itemBuff[0].equals("Health")) {
      int newHealth = this.stats.get("Health") + Integer.parseInt(itemBuff[1]);

      if (newHealth >= 100)
        this.stats.replace("Health", 100);
      else
        this.stats.replace("Health", newHealth);
    } else if (itemBuff[0].equals("Hunger")) {
      int newHunger = this.stats.get("Hunger") + Integer.parseInt(itemBuff[1]);
      if (newHunger >= 100)
        this.stats.replace("Hunger", 100);
      else
        this.stats.replace("Hunger", newHunger);
    } else if (itemBuff[0].equals("Hydration")) {
      int newHydration = this.stats.get("Hydration") + Integer.parseInt(itemBuff[1]);
      if (newHydration >= 100)
        this.stats.replace("Hydration", 100);
      else
        this.stats.replace("Hydration", newHydration);
    }
  }

  public void setName(String name) {
    this.name = name;
  }

  public void setTurn(int turn) {
    this.turn = turn;
  }

  public void setCurrentRod(Item currentRod) {
    this.currentRod = currentRod;
  }

  public void setFishingBuff(int fishingBuff) {
    this.fishingBuff = fishingBuff;
  }
}
