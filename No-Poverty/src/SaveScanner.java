
import java.util.InputMismatchException;
import java.util.Scanner;

public class SaveScanner {

  Scanner scan = new Scanner(System.in);

  public int nextInt(String prompt, boolean log) {
    // Input handling
    int intInput;
    if (log) {
      System.out.println(prompt);
    }
    try {
      intInput = scan.nextInt();

    } catch (InputMismatchException e) {
      System.out.println("Must be a number!");
      return nextInt(prompt, log);
    }
    return intInput;
  }

  public double nextDouble(String prompt, boolean log) {
    // Input handling
    double doubleInput;
    if (log) {
      System.out.println(prompt);
    }
    try {
      doubleInput = scan.nextDouble();

    } catch (InputMismatchException e) {
      System.out.println("Must be a number!");
      return nextDouble(prompt, log);
    }
    return doubleInput;
  }

  public String nextLine(String prompt, boolean log) {
    String input;

    if (log) {
      System.out.println(prompt);
    }

    input = scan.nextLine();
    return input;
  }

  public void closeScan() {
    scan.close();
  }

}