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
    indent(n); tree("if ( "); cond.dumpAST(0); tree(" )\n");
    indent(n); tree("{\n");
    thenStmt.dumpAST(n + 1);
    indent(n); tree("}\n");
    if (elseStmt != null) {
      indent(n); tree("else\n"); indent(n); tree("{\n");
      elseStmt.dumpAST(n + 1);
      indent(n); tree("}\n");
    }
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
  }
}
