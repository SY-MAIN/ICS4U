package ASCII_ART;

import java.io.File;

/**
 * This ASCIIART_setting class stores important information about the size of
 * the ascii art.
 * 
 * @author Simon Yang
 * @version 1.0
 * @since 2021-11-21
 */
public class ASCIIART_setting {
  // ================================================
  // Global variables to the Game class
  // ================================================
  public static File ArtDir = new File("./ASCII_ART");
  public static int WIDTH = 70;
  public static int HEIGHT = 20;

  /**
   * This method returns the actual file.
   * 
   * @param fileName The file name to be access.
   * @return File returns the actual file.
   */
  public static File returnFile(String fileName) {
    return new File(ArtDir, fileName);
  }
}
