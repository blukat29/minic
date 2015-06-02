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
    indent(n); tree("FLOAT:" + value + "\n");
  }
  public void analyse(Scope scope) {
    this.ty = new TypeInfo(TypeInfo.FLOAT);
  }
  public void codegen() {
    this.reg = nextReg();
    code(String.format("MOVE %f VR(%d)", value, reg));
  }
}
