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
      return String.format("do{\n%s } while( %s );", body, cond);
    else
      return String.format("while( %s ) {\n %s }", cond, body);
  }
}
