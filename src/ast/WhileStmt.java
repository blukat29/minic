package ast;
public class WhileStmt extends Stmt {
  private Expr cond;
  private Stmt body;
  private boolean isDoWhile;
  public WhileStmt(Expr cond, Stmt body, boolean isDoWhile) {
    this.cond = cond;
    this.body = body;
    this.isDoWhile = isDoWhile;
  }
  public String toString() {
    if (isDoWhile)
      return String.format("do\n{\n%s} while( %s );\n", body, cond);
    else
      return String.format("while( %s )\n{\n%s}\n", cond, body);
  }
}
