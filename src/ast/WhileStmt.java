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
      indent(n); tree("do\n"); indent(n); tree("{\n");
      body.dumpAST(n + 1);
      indent(n); tree("} while ( "); cond.dumpAST(0); tree(" );\n");
    }
    else {
      indent(n); tree("while ( "); cond.dumpAST(0); tree(" )\n");
      indent(n); tree("{\n");
      body.dumpAST(n + 1);
      indent(n); tree("}\n");
    }
  }

  public void compile(Scope scope) {
    cond.compile(scope);
    body.compile(scope);
    if (cond.isArray) {
      error("An array cannot be boolean", cond);
      return;
    }
  }
}
