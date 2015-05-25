package ast;
import symbol.*;

public class FloatNum extends Expr {
  float value;
  public FloatNum(String s) {
    this(null, s);
  }
  public FloatNum(Pos pos, String s) {
    super(pos);
    value = Float.parseFloat(s);
  }
  public void dumpAST(int n) {
    indent(n); tree(value + "f");
  }
  public void compile(Scope scope) {
    this.ty = new TypeInfo(TypeInfo.FLOAT);
  }
}
