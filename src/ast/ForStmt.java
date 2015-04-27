package ast;
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
  public String toString() {
    String s = String.format("for ( %s ; %s ; %s )", init, cond, incr);
    s += "\n{\n" + body + "}\n";
    return s;
  }
}
