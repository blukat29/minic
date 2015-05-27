package ast;
import symbol.*;

public class WhileStmt extends Stmt {
  private Expr cond;
  private Stmt body;
  private boolean isDoWhile;

  public WhileStmt(Expr cond, Stmt body, boolean isDoWhile) {
    this.cond = cond;
    this.body = body;
    this.isDoWhile = isDoWhile;
  }

  public void dumpAST(int n) {
    if (isDoWhile) {
      indent(n); tree("WhileStmt(do-while)\n");
      body.dumpAST(n+1);
      cond.dumpAST(n+1);
    }
    else {
      indent(n); tree("WhileStmt(while)\n");
      cond.dumpAST(n+1);
      body.dumpAST(n+1);
    }
  }

  public void analyse(Scope scope) {
    cond.analyse(scope);
    body.analyse(scope);
    if (cond.isArray) {
      error("An array cannot be boolean", cond);
      return;
    }
  }
}
