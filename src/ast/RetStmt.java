package ast;
public class RetStmt extends Stmt {
  private Expr expr;
  public RetStmt() {
    this(null);
  }
  public RetStmt(Expr expr) {
    this.expr = expr;
  }
  public String toString() {
    if (expr == null)
      return "ret";
    else
      return "ret " + expr;
  }
}
