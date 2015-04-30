package ast;
import java.io.*;
import java.util.*;

public class SourceManager {
  private String code;

  public SourceManager(InputStream source) {
    /* Read until input ends */
    Scanner scanner = new Scanner(source).useDelimiter("\\A");
    if (scanner.hasNext()) code = scanner.next();
    else code = "";
  }

  public Reader getReader() {
    return new StringReader(code);
  }

  public void showPart(int lineNo, int column) {
    Scanner scanner = new Scanner(code);
    int i = 1;
    String line = "";
    while (scanner.hasNextLine() && i <= lineNo) {
      line = scanner.nextLine();
      i ++;
    }
    if (i == lineNo + 1) {
      System.err.print("    " + line + "\n    ");
      for (int j=1; j<column; j++)
        System.err.print(" ");
      System.err.println("^");
    }
  }
}
