package ast;
public class CallStmt extends Stmt {
  private CallExpr call;
  public CallStmt(CallExpr call) {
    this.call = call;
  }
  public String toString() {
    return call + ";\n";
  }
}
