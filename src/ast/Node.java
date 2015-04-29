package ast;

abstract public class Node {
  private int row;
  private int column;
  public abstract void dumpAST(int indent);

  private void ast(String s) {
    Printer.astWriter.write(s);
  }
  private void indent(int n) {
    Printer.astWriter.indent(n);
  }
}
