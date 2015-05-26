package ast;
import symbol.*;

public class ForStmt extends Stmt {
  private Assign init;
  private Expr cond;
  private Assign incr;
  private Stmt body;

  public ForStmt(Assign init, Expr cond, Assign incr, Stmt body) {
    this.init = init;
    this.cond = cond;
    this.incr = incr;
    this.body = body;
  }

  public void dumpAST(int n) {
    indent(n); tree("ForStmt\n");
    init.dumpAST(n+1);
    cond.dumpAST(n+1);
    incr.dumpAST(n+1);
    body.dumpAST(n+1);
  }

  public void compile(Scope scope) {
    init.compile(scope);
    cond.compile(scope);
    incr.compile(scope);
    body.compile(scope);
    if (cond.isArray) {
      error("An array cannot be boolean", cond);
      return;
    }
  }
}
