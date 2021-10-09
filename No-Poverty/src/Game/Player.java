package Game;

public class Player {
  private String name = "";
  private char ID;
  private String status = "Poor";
  int numOfTerritory = 1;
  double mutationRate = 0.2; // 0.2 territory per second or 1 territory per 5 second

  public Player(String name, char ID) {
    this.name = name;
    this.ID = ID;
  }

  public void StatusChange() {
    this.status = "rich";
    this.numOfTerritory = 5;
    this.mutationRate = 1;
  }

  public String getName() {
    return this.name;
  }

  public char getId() {
    return this.ID;
  }

  public String getStatus() {
    return this.status;
  }

  public int calTime() {
    // return the mutationRate in milliseconds
    return (int) (1 / this.mutationRate) * 1000;
  }

}
