package ast;
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
  public String toString() {
    String s = "if( " + cond + " )";
    s += "\n{ " + thenStmt + " }";
    if (elseStmt != null)
      s += "\nelse { " + elseStmt + " }";
    return s;
  }
}
