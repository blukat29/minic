package ast;
import symbol.*;

public class CompoundStmt extends Stmt {
  private DeclList declList;
  private StmtList stmtList;
  public CompoundStmt(StmtList sl) {
    this(null, sl);
  }
  public CompoundStmt(DeclList dl, StmtList sl) {
    declList = dl;
    stmtList = sl;
  }
  public String toString() {
    String s = "{\n";
    if (declList != null)
      s += declList + "\n";
    s += stmtList + "}\n";
    return s;
  }

  public void compile(Scope scope) {
    scope.push(this);
    if (declList != null)
      declList.compile(scope);
    scope.pop();
  }
}
