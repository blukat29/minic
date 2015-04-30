package ast;
import java.io.*;

public class Printer {
  private static final String indent = "  ";
  private Writer writer;
  public static Printer astWriter;

  public Printer(Writer writer) {
    this.writer = writer;
  }

  public static void setASTWriter(Writer writer) {
    astWriter = new Printer(writer);
  }

  public void write(String s) {
    _write(s);
  }

  public void indent(int n) {
    String s = "";
    for (int i=0; i<n; i++) s += indent;
    _write(s);
  }

  private void _write(String s) {
    try {
      writer.write(s);
    }
    catch (IOException e) {
      System.out.println("I/O error: " + e.getMessage());
      e.printStackTrace();
      System.exit(1);
    }
  }
}
