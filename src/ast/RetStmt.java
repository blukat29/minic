package ast;
import symbol.*;

public class RetStmt extends Stmt {
  private Expr expr;

  public RetStmt() {
    this(null);
  }
  public RetStmt(Expr expr) {
    this.expr = expr;
  }

  public void dumpAST(int n) {
    if (expr == null) {
      indent(n); tree("return;\n");
    }
    else {
      indent(n); tree("return "); expr.dumpAST(0); tree(";\n");
    }
  }

  public void compile(Scope scope) {
    expr.compile(scope);
  }
}
