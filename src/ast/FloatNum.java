package ast;
import symbol.*;

public class FloatNum extends Expr {
  float value;
  public FloatNum(String s) {
    value = Float.parseFloat(s);
  }
  public void dumpAST(int n) {
    indent(n); tree(value + "f");
  }
  public void compile(Scope scope) {
  }
}
