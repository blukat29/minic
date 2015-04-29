package ast;
import symbol.*;

public class UnOpExpr extends Expr {
  private String op;
  private Expr r;

  public UnOpExpr(String op, Expr r) {
    this.op = op;
    this.r = r;
  }

  public void dumpAST(int indent) {
    ASTWriter.write(op + " ( ", indent);
    r.dumpAST(0);
    ASTWriter.write(" )");
  }

  public void compile(Scope scope) {
    r.compile(scope);
  }
}
