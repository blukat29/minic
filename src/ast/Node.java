package ast;

abstract public class Node {
  private int row;
  private int column;
  public abstract void dumpAST(int indent);

  protected void tree(String s) {
    Printer.astWriter.write(s);
  }
  protected void indent(int n) {
    Printer.astWriter.indent(n);
  }
  protected void error(String s) {
    System.err.println(s);
  }
}
