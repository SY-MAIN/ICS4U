package Game;

import java.util.*;

/**
 * This item class stores the information about each item in the game.
 * 
 * @author Simon Yang
 * @version 1.0
 * @since 2021-11-21
 */
public class Item {
  // ================================================
  // Global variables to the Game class
  // ================================================
  private String name;
  private String ID;
  private boolean isUseable;
  private boolean isCraftable;

  private String[] buff = new String[2]; // Storing the name and the status
  private HashMap<Item, Integer> recipe = new HashMap<Item, Integer>();

  /**
   * This is a class constructor that initialize the global variables.
   * 
   * @param name        The name of the item
   * @param ID          The ID of the item
   * @param isUseAble   The isUseAble of the item
   * @param isCraftable The isCraftable of the item
   */
  public Item(String name, String ID, boolean isUseable, boolean isCraftable) {
    this.name = name;
    this.ID = ID;
    this.isUseable = isUseable;
    this.isCraftable = isCraftable;
  }

  /**
   * This method returns the name of the item
   * 
   * @return String return the name of the item
   */
  public String getName() {
    return this.name;
  }

  /**
   * This method returns the ID of the item
   * 
   * @return String return the ID of the item
   */
  public String getID() {
    return this.ID;
  }

  /**
   * This method returns if the item is useable
   * 
   * @return boolean return the isUseAble of the item
   */
  public boolean isUseable() {
    return this.isUseable;
  }

  /**
   * This method returns if the item is craftable or not
   * 
   * @return boolean return the isCraftable of the item
   */
  public boolean isCraftable() {
    return this.isCraftable;
  }

  /**
   * This method returns the item buff
   * 
   * @return boolean return the buff of the item
   */
  public String[] getBuff() {
    return this.buff;
  }

  /**
   * This method returns the recipe to craft the item
   * 
   * @return boolean return the recipe to craft the item
   */
  public HashMap<Item, Integer> getRecipe() {
    return this.recipe;
  }

  /**
   * This method adds items to the recipe during initialize
   * 
   * @param item     The item to add to recipe
   * @param quantity The amount required to craft the item
   * @return void
   */
  public void addRecipe(Item item, int quantity) {
    this.recipe.put(item, quantity);
  }

  /**
   * This method saves the buff of the item.
   * 
   * @param name  The name of the buff
   * @param stats The amount of stats when buff is used
   * @return void
   */
  public void addBuff(String name, int stats) {
    this.buff[0] = name;
    this.buff[1] = Integer.toString(stats);
  }
}
