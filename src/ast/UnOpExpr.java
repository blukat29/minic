package ast;
public class UnOpExpr extends Expr {
  private String op;
  private Expr r;
  public UnOpExpr(String op, Expr r) {
    this.op = op;
    this.r = r;
  }
  public String toString() {
    return String.format("%s(%s)", op, r);
  }
}
