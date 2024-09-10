
import java.io.*; // Import the FileWriter class
// import java.util.*; // Import the FileWriter class

public class FileHandling {
  public static void main(String[] args) {
    try {
      FileReader myReader = new FileReader("filename.txt");
      
      myReader.close();
    } catch (Exception e) {
      System.out.println("An error occurred.");
      e.printStackTrace();
    }
  }
}