package ast;
import java.io.*;

public class ASTWriter {
  private static final String indent = "  ";
  private static Writer writer;

  public static void setWriter(Writer writer_) {
    writer = writer_;
  }

  private static String indentation(int n) {
    String s = "";
    for (int i=0; i<n; i++) s += indent;
    return s;
  }

  public static void write(String s, int n) {
    try {
      writer.write(indentation(n));
      writer.write(s);
    }
    catch (IOException e) {
      System.out.println("I/O error: " + e.getMessage());
      e.printStackTrace();
    }
  }
  public static void write(String s) {
    write(s, 0);
  }
}
