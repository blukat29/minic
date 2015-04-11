package ast;
public class BinOpExpr extends Expr {
  private Expr l;
  String op;
  private Expr r;
  public BinOpExpr(Expr l, String op, Expr r) {
    this.l = l;
    this.op = op;
    this.r = r;
  }
  public String toString() {
    return String.format("(%s %s %s)", l, op, r);
  }
}

