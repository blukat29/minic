package ast;
import symbol.*;

public class UnOpExpr extends Expr {
  private String op;
  private Expr r;

  public UnOpExpr(String op, Expr r) {
    this.op = op;
    this.r = r;
  }

  public void dumpAST(int n) {
    indent(n); tree(op + " ( "); r.dumpAST(0); tree(" )");
  }

  public void compile(Scope scope) {
    r.compile(scope);
  }
}
