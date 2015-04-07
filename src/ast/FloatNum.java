package ast;
public class FloatNum extends Expr {
  float value;
  public FloatNum(String s) {
    value = Float.parseFloat(s);
  }
  public String toString() {
    return value + "f";
  }
}
