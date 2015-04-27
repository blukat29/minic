package ast;
public class CallStmt extends Stmt {
  private CallExpr call;
  public CallStmt(CallExpr call) {
    this.call = call;
  }
  public void dumpAST(int indent) {
    call.dumpAST(indent);
    ASTWriter.write(";\n");
  }
}
