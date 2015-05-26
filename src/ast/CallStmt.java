package ast;
import symbol.*;

public class CallStmt extends Stmt {
  private CallExpr call;
  public CallStmt(CallExpr call) {
    this.call = call;
  }
  public void dumpAST(int n) {
    call.dumpAST(n);
  }
  public void compile(Scope scope) {
    call.compile(scope);
  }
}
