import java.io.File;

public class Render {
  public static File ArtDir = new File("./ASCII_ART");
  public static int WIDTH = 70;
  public static int HEIGHT = 20;

  public static void displayScreen(String fileName) {

    File newFile = new File(ArtDir, fileName);

    System.out.println(FileLoader.readFile(newFile));
  }

  public static void displayCenterText(String items[], int totalWidth) {
    System.out.println();

    for (int i = 0; i < items.length; i++) {
      String current = items[i];
      int spaceLeft = totalWidth - current.length();

      int left = spaceLeft / 2;
      int right = left + current.length();

      // Print left amount of spaces
      for (int j = 0; j < left; j++) {
        System.out.print(" ");
      }
      System.out.print(current);

      // Print right amount of spaces
      for (int j = 0; j < right; j++) {
        System.out.print(" ");
      }

      System.out.println("\n");
    }
  }

  public static int menuScreen(String option[]) {
    Render.displayCenterText(option, WIDTH);

    return SaveScanner.nextInt("", false);
  }
}
