package Game;

public class Player {
  String name = "";
  String status = "Poor";

  public Player(String name) {
    this.name = name;
  }

  public void mutation() {
    this.status = "rich";
  }
}
