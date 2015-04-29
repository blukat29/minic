package ast;
import symbol.*;

public class FloatNum extends Expr {
  float value;
  public FloatNum(String s) {
    value = Float.parseFloat(s);
  }
  public void dumpAST(int indent) {
    ASTWriter.write(value + "f", indent);
  }
  public void compile(Scope scope) {
  }
}
