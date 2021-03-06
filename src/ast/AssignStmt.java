package ast;
import symbol.*;

public class AssignStmt extends Stmt {
  private Assign assign;
  public AssignStmt(Assign assign) {
    this.assign = assign;
  }
  public void dumpAST(int n) {
    assign.dumpAST(n);
  }
  public void analyse(Scope scope) {
    assign.analyse(scope);
  }
  public void codegen() {
    code("// ------ AssignStmt");
    assign.codegen();
  }
}
