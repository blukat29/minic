package ast;
import symbol.*;

public class IntNum extends Expr {
  private int value;
  public IntNum(String s) {
    value = Integer.parseInt(s);
  }
  public int getValue() {
    return value;
  }
  public void dumpAST(int indent) {
    ASTWriter.write(value + "i", indent);
  }
  public void compile(Scope scope) {
  }
}
