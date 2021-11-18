package ASCII_ART;

import java.io.File;

public class ASCIIART_setting {
  public static File ArtDir = new File("./ASCII_ART");
  public static int WIDTH = 70;
  public static int HEIGHT = 20;

  public static File returnFile(String fileName) {
    return new File(ArtDir, fileName);
  }
}
