package ast;

abstract public class Node {
  private Pos pos;
  private static int errorCount = 0;
  private static int warnCount = 0;
  private static int nextReg = 0;

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
  protected void code(String s) {
    Printer.codeWriter.write(s + "\n");
  }
  protected void error(String s) {
    System.err.println(String.format("error at %s: %s", this.pos, s));
    pos.showSource();
    errorCount ++;
  }
  protected void error(String s, Node target) {
    System.err.println(String.format("error at %s: %s", target.pos, s));
    target.pos.showSource();
    errorCount ++;
  }
  protected void warn(String s, Node target) {
    System.err.println(String.format("warning at %s: %s", target.pos, s));
    target.pos.showSource();
    warnCount ++;
  }
  public static int getErrorCount() {
    return errorCount;
  }
  public static int getWarnCount() {
    return warnCount;
  }

  public static int nextReg() {
    nextReg ++;
    return nextReg;
  }
  public static void resetReg() {
    nextReg = 0;
  }
}
