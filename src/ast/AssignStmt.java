package ast;
public class AssignStmt extends Stmt {
  private Assign assign;
  public AssignStmt(Assign assign) {
    this.assign = assign;
  }
  public void dumpAST(int indent) {
    assign.dumpAST(indent);
    ASTWriter.write(";\n");
  }
}
