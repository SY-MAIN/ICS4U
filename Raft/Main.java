import java.io.File;

class Main {
  public static void main(String[] args) {

    File ArtDir = new File("./ASCII_ART");

    String fileName = "Idle";

    File newFile = new File(ArtDir, fileName);
    System.out.println(newFile.exists());

    System.out.println(FileLoader.readFile(newFile));

  }
}