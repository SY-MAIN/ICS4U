package Game;

public class Item {
  private String name;
  private int quantity;
  private boolean isUseable;
  private int turn = 0;

  public String getName() {
    return this.name;
  }

  public boolean isUseable() {
    return this.isUseable;
  }
}
