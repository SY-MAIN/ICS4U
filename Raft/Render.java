import java.util.Scanner;

import ASCII_ART.ASCIIART_setting;

public class Render {

  private static Scanner scan = new Scanner(System.in);

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
    Render.displayCenterText(option, ASCIIART_setting.WIDTH);

    return scan.nextInt();
  }
}
