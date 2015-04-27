package ast;
public class AssignStmt extends Stmt {
  private Assign assign;
  public AssignStmt(Assign assign) {
    this.assign = assign;
  }
  public String toString() {
    return assign + ";\n";
  }
}
