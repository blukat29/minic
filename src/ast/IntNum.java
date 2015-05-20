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
  public void dumpAST(int n) {
    indent(n); tree(value + "i");
  }
  public void compile(Scope scope) {
    this.ty = new TypeInfo(TypeInfo.INT);
  }
}
