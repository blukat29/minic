package ast;
import symbol.*;

public class CallStmt extends Stmt {
  private CallExpr call;
  public CallStmt(CallExpr call) {
    this.call = call;
  }
  public void dumpAST(int indent) {
    call.dumpAST(indent);
    ASTWriter.write(";\n");
  }
  public void compile(Scope scope) {
  }
}
