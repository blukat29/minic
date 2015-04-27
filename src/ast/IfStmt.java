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
    s += "\n{\n" + thenStmt + "}\n";
    if (elseStmt != null)
      s += "else\n{\n" + elseStmt + "}\n";
    return s;
  }
}
