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
    indent(n); tree("for ( "); init.dumpAST(0); tree(" ; ");
    cond.dumpAST(0); tree(" ; "); incr.dumpAST(0); tree(" )\n");
    indent(n); tree("{\n");
    body.dumpAST(n + 1);
    indent(n); tree("}\n");
  }

  public void compile(Scope scope) {
    init.compile(scope);
    cond.compile(scope);
    incr.compile(scope);
    body.compile(scope);
  }
}
