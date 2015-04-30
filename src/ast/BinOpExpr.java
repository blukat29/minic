package ast;
import symbol.*;

public class BinOpExpr extends Expr {
  private Expr l;
  String op;
  private Expr r;

  public BinOpExpr(Expr l, String op, Expr r) {
    this.l = l;
    this.op = op;
    this.r = r;
  }

  public void dumpAST(int n) {
    tree("("); l.dumpAST(n); tree(" " + op + " "); r.dumpAST(0); tree(")");
  }

  public void compile(Scope scope) {
    l.compile(scope);
    r.compile(scope);
  }
}

