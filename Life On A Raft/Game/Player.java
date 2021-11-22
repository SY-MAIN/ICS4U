package Game;

import java.util.*;

/**
 * This Game class implements the game interface and logics of the game.
 * 
 * @author Simon Yang
 * @version 1.0
 * @since 2021-11-21
 */
public class Player {
  // ================================================
  // Global variables to the Player class
  // ================================================
  private int turn = 0;

  private Item currentRod = null;
  private int fishingBuff = 1;

  // Stats to deduct from the player.
  private int hungerPerTurn = 5; // Lose 20 hunger for each turn
  private int hydrationTurn = 5; // Gain 25 hydration for each turn
  private int healthPerTurn = 10; // Lose 10 health for each turn that is hungry/hydrated

  public boolean gameOver = false;

  // Information about the user
  public HashMap<String, Integer> stats = new HashMap<String, Integer>(); // Health, Hunger, Hydration
  public HashMap<Item, Integer> inventory = new HashMap<Item, Integer>();

  /**
   * This method calculates the next turn for the player. It calculates the
   * health, hungry and hydration per turn.
   * 
   * @return void
   * 
   */
  public void nextTurn() {
    this.turn++;

    // Deduct hungry per turn and when hungry is less than or equal to 0 then deduct
    // health. While also preventing stats from showing negative stats.
    if (this.stats.get("Hunger") <= 0) {
      // Take away Health
      this.stats.replace("Health", this.stats.get("Health") - this.healthPerTurn);
    } else if (this.stats.get("Hunger") - this.hungerPerTurn > 0) {
      // Take away Hunger
      this.stats.replace("Hunger", this.stats.get("Hunger") - this.hungerPerTurn);
    } else if (this.stats.get("Hunger") - this.hungerPerTurn <= 0) {
      // Prevent stats showing negative stats.
      this.stats.replace("Hunger", 0);
    }

    // Deduct Hydration per turn and when Hydration is less than or equal to 0 then
    // deduct health. While also preventing stats from showing negative stats.
    if (this.stats.get("Hydration") <= 0) {
      // Take away Health
      this.stats.replace("Health", this.stats.get("Health") - this.healthPerTurn);
    } else if (this.stats.get("Hydration") - this.hydrationTurn > 0) {
      // Take away hydration
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

  /**
   * This method applies the item buff to the user.
   * 
   * @param item The item that is being used.
   * @return void
   * 
   */
  public void useItem(Item item) {
    String[] itemBuff = item.getBuff();
    // Check if the item can apply a buff.
    if (itemBuff.length == 0) {
      // item buff is NA
      return;
    }

    // If the buff is an item buff otherwise its a status buff
    if (itemBuff[0].equals("itemBuff")) {
      this.currentRod = item;
      this.fishingBuff = Integer.parseInt(itemBuff[1]);
    } else if (itemBuff[0].equals("Health")) {
      int newHealth = this.stats.get("Health") + Integer.parseInt(itemBuff[1]);

      // Apply health only when the status is not over 100
      if (newHealth >= 100)
        this.stats.replace("Health", 100);
      else
        this.stats.replace("Health", newHealth);
    } else if (itemBuff[0].equals("Hunger")) {
      int newHunger = this.stats.get("Hunger") + Integer.parseInt(itemBuff[1]);

      // Apply health only when the status is not over 100
      if (newHunger >= 100)
        this.stats.replace("Hunger", 100);
      else
        this.stats.replace("Hunger", newHunger);
    } else if (itemBuff[0].equals("Hydration")) {
      int newHydration = this.stats.get("Hydration") + Integer.parseInt(itemBuff[1]);

      // Apply health only when the status is not over 100
      if (newHydration >= 100)
        this.stats.replace("Hydration", 100);
      else
        this.stats.replace("Hydration", newHydration);
    }
  }

  /**
   * This method is used to set the value of turns.
   * 
   * @param turn The amount of turns.
   * @return void
   * 
   */
  public void setTurn(int turn) {
    this.turn = turn;
  }

  /**
   * This method is used to set the the current type rod
   * 
   * @param currentRod The type of rod.
   * @return void
   * 
   */
  public void setCurrentRod(Item currentRod) {
    this.currentRod = currentRod;
  }

  /**
   * This method is used to set the fishing buff
   * 
   * @param fishingBuff The amount of fishing buff.
   * @return void
   * 
   */
  public void setFishingBuff(int fishingBuff) {
    this.fishingBuff = fishingBuff;
  }

  /**
   * This method returns the amount fishing buff
   * 
   * @return int returns the amount fishing buff
   * 
   */
  public int getFishingBuff() {
    return this.fishingBuff;
  }

  /**
   * This method returns the amount of turns
   * 
   * @return int returns the amount of turns
   * 
   */
  public int getTurn() {
    return this.turn;
  }

  /**
   * This method returns the current rod
   * 
   * @return Item returns the current fishing rod
   * 
   */
  public Item getCurrentRod() {
    return this.currentRod;
  }

  /**
   * This method returns the current health
   * 
   * @return int returns the current health
   * 
   */
  public int getHealth() {
    return this.stats.get("Health");
  }

  /**
   * This method returns the current hungry
   * 
   * @return int returns the current hungry
   * 
   */
  public int getHunger() {
    return this.stats.get("Hunger");
  }

  /**
   * This method returns the current hydration
   * 
   * @return int returns the current hydration
   * 
   */
  public int getHydration() {
    return this.stats.get("Hydration");
  }
}
