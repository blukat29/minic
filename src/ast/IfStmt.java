package ast;
import symbol.*;

public class IfStmt extends Stmt {
  private Expr cond;
  private Stmt thenStmt;
  private Stmt elseStmt;

  public IfStmt(Expr c, Stmt t) {
    this(c, t, null);
  }
  public IfStmt(Expr c, Stmt t, Stmt e) {
    cond = c;
    thenStmt = t;
    elseStmt = e;
  }

  public void dumpAST(int n) {
    indent(n); tree("IfStmt\n");
    cond.dumpAST(n+1);
    thenStmt.dumpAST(n+1);
    if (elseStmt != null)
      elseStmt.dumpAST(n+1);
  }

  public void compile(Scope scope) {
    cond.compile(scope);
    thenStmt.compile(scope);
    if (elseStmt != null)
      elseStmt.compile(scope);
    if (cond.isArray) {
      error("An array cannot be boolean", cond);
      return;
    }
    if (elseStmt == null && thenStmt.hasReturned)
      this.hasReturned = true;
    if (elseStmt != null && thenStmt.hasReturned && elseStmt.hasReturned)
      this.hasReturned = true;
  }
}
