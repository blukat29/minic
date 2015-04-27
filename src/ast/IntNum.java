package ast;
public class IntNum extends Expr {
  private int value;
  public IntNum(String s) {
    value = Integer.parseInt(s);
  }
  public int getValue() {
    return value;
  }
  public String toString() {
    return value + "i";
  }
}
