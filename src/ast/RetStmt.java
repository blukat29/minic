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
    indent(n); tree("ReturnStmt\n");
    if (expr != null)
      expr.dumpAST(n+1);
  }

  public void compile(Scope scope) {
    if (expr != null)
      expr.compile(scope);
    if (expr.isArray) {
      error("Cannot return an array", expr);
      return;
    }
    if (expr.ty == null) return;
    TypeInfo retTy = scope.getFunction().getRetTy();
    if (!expr.ty.equals(retTy)) {
      warn(String.format("Implicitly casting %s to %s", expr.ty, retTy), expr);
      expr = new TypeCast(retTy, expr);
      expr.compile(scope);
    }
    hasReturned = true;
  }
}
