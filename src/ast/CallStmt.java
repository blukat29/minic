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
  public void analyse(Scope scope) {
    call.analyse(scope);
  }
  public void codegen() {
    code("// CallStmt, not implemented");
  }
}
