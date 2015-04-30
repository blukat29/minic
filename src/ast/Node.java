package ast;

abstract public class Node {
  private Pos pos;

  protected Node() {
    this(new Pos());
  }
  protected Node(Pos pos) {
    this.pos = pos;
  }

  public abstract void dumpAST(int indentLevel);

  protected void tree(String s) {
    Printer.astWriter.write(s);
  }
  protected void indent(int n) {
    Printer.astWriter.indent(n);
  }
  protected void error(String s) {
    System.err.println(String.format("Error at %s: %s", pos, s));
    pos.showSource();
  }
  protected void error(String s, Node target) {
    System.err.println(String.format("Error at %s: %s", pos, s));
    target.pos.showSource();
  }
}
