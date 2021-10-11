
import java.util.InputMismatchException;
import java.util.Scanner;

public class SaveScanner {

  Scanner scan = new Scanner(System.in);

  public int nextInt(String prompt, boolean log) {
    // Input handling
    int intInput;

    // Display prompt if necessary
    if (log) {
      System.out.println(prompt);
    }

    // Try getting inputs from the user. If the user tries to enter anything other
    // than an int, then the program will display a warning.
    try {
      // Get the inputs from the user.
      intInput = scan.nextInt();

    } catch (InputMismatchException e) {
      // Display an warning.
      System.out.println("Must be a number!");
      // call the method itself to continue getting inputs.
      return nextInt(prompt, log);
    }
    // return the input.
    return intInput;
  }

  public double nextDouble(String prompt, boolean log) {
    // Input handling
    double doubleInput;

    // Display prompt if necessary
    if (log) {
      System.out.println(prompt);
    }

    // Try getting inputs from the user. If the user tries to enter anything other
    // than an double, then the program will display a warning.
    try {
      // Get the inputs from the user.
      doubleInput = scan.nextDouble();

    } catch (InputMismatchException e) {
      // Display an warning.
      System.out.println("Must be a number!");
      // call the method itself to continue getting inputs.
      return nextDouble(prompt, log);
    }
    // return the input.
    return doubleInput;
  }

  public String nextLine(String prompt, boolean log) {
    String input;

    // Display prompt if necessary
    if (log) {
      System.out.println(prompt);
    }
    // Get the inputs from the user.
    input = scan.nextLine();
    return input;
  }
}