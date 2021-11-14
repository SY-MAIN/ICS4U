package Game;

import java.util.*;

public class Item {
  private String name;
  private String ID;
  private boolean isUseable;
  private boolean isCraftable;
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

  public void addRecipe(Item item, int quantity) {
    this.recipe.put(item, quantity);
  }
}
