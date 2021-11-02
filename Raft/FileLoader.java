import java.util.Scanner;
import java.io.File;
import java.io.IOException;
import java.io.FileWriter;

public class FileLoader {
  public static void WriteToFile(File file, String[] args) {
    try {
      FileWriter writer = new FileWriter(file, true);
      for (int i = 0; i < args.length; i++) {
        writer.write(args[i]);
      }
      System.out.println("File Saved");
      writer.close();
    } catch (IOException e) {
      System.err.println("Java Exception: " + e);
    }
  }

  public static void WriteToFile(File file, String[][] args) {
    try {
      FileWriter writer = new FileWriter(file);
      for (int i = 0; i < args.length; i++) {
        for (int j = 0; j < args[i].length; j++) {
          writer.write(args[i][j]);
        }
        writer.write("\n");
      }
      System.err.println("File Saved");
      writer.close();
    } catch (IOException e) {
      System.out.println("Java Exception: " + e);
    }
  }

  public static String readFile(File file) {
    String output = "";
    try {
      Scanner fileInput = new Scanner(file);

      while (fileInput.hasNextLine()) {
        output += fileInput.nextLine();
        output += "\n";
      }
      System.out.println("Files Loaded");
      fileInput.close();
    } catch (IOException e) {
      System.err.println("Java Exception: " + e);
    }
    return output;
  }

  // ================================================
  // Helper methods
  // ================================================

  public static void removeFile(File file) {
    if (file.exists()) {
      file.delete();
      System.out.println("ADMIN: File Deleted");
    } else {

      System.out.println("ADMIN: File NOT FOUND");
    }
  }
}
