package Game;

import java.util.*;

public class Item {
  private String name;
  private String ID;
  private boolean isUseable;
  private boolean isCraftable;
  private String[] buff = new String[2]; // Storing the name and the status
  private HashMap<Item, Integer> recipe = new HashMap<Item, Integer>();

  public Item(String name, String ID, boolean isUseable, boolean isCraftable) {
    this.name = name;
    this.ID = ID;
    this.isUseable = isUseable;
    this.isCraftable = isCraftable;
  }

  public String getName() {
    return this.name;
  }

  public String getID() {
    return this.ID;
  }

  public boolean isUseable() {
    return this.isUseable;
  }

  public boolean isCraftable() {
    return this.isCraftable;
  }

  public String[] getBuff() {
    return this.buff;
  }

  public HashMap<Item, Integer> getRecipe() {
    return this.recipe;
  }

  public void addRecipe(Item item, int quantity) {
    this.recipe.put(item, quantity);
  }

  public void addBuff(String name, int stats) {
    this.buff[0] = name;
    this.buff[1] = Integer.toString(stats);
  }
}
