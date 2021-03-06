package ast;
import symbol.*;

public class IntNum extends Expr {
  private int value;
  public IntNum(String s) {
    this(null, s);
  }
  public IntNum(Pos pos, String s) {
    super(pos);
    value = Integer.parseInt(s);
  }
  public int getValue() {
    return value;
  }
  public void dumpAST(int n) {
    indent(n); tree("INT:" + value + "\n");
  }
  public void analyse(Scope scope) {
    this.ty = new TypeInfo(TypeInfo.INT);
  }
  public String toString() {
    return Integer.toString(value);
  }
  public void codegen() {
    this.reg = nextReg();
    code(String.format("MOVE %d VR(%d)", value, reg));
  }
}
