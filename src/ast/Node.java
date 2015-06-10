package ast;
import symbol.*;

abstract public class Node {
  protected Pos pos;
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

  protected String getMemoryAccess(Symbol symbol) {
    if (symbol.isGlobal())
      return String.format("MEM(%d)", symbol.getOffset());
    else
      return String.format("MEM(FP@(%d))", symbol.getOffset());
  }
  protected String getMemoryAccess(Symbol symbol, int idxReg) {
    if (!symbol.isGlobal()) {
      if (symbol.isParam() && symbol.isArray()) { // MEM[FP + ofs] + idx
        int addrReg = nextReg();
        code(String.format("MOVE MEM(FP@(%d))@ VR(%d)", symbol.getOffset(), addrReg));
        code(String.format("ADD VR(%d)@ VR(%d)@ VR(%d)", addrReg, idxReg, addrReg));
        return String.format("MEM(VR(%d)@)", addrReg);
      }
      else // FP + ofs + idx
        code(String.format("ADD VR(%d)@ FP@ VR(%d)",idxReg, idxReg));
    }
    return String.format("MEM(VR(%d)@(%d))", idxReg, symbol.getOffset());
  }
}
