package ast;
import symbol.*;

public class AssignStmt extends Stmt {
  private Assign assign;
  public AssignStmt(Assign assign) {
    this.assign = assign;
  }
  public void dumpAST(int n) {
    assign.dumpAST(n); tree(";\n");
  }
  public void compile(Scope scope) {
    assign.compile(scope);
  }
}
