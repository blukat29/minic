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

  public void dumpAST(int n) {
    indent(n); tree("CompoundStmt\n");
    if (declList != null)
      declList.dumpAST(n + 1);
    stmtList.dumpAST(n + 1);
  }

  public void compile(Scope scope) {
    scope.push(this);
    if (declList != null)
      declList.compile(scope);
    stmtList.compile(scope);
    this.hasReturned = stmtList.hasReturned;
    scope.pop();
  }

  public void compileFunctionBlock(Scope scope) {
    if (declList != null)
      declList.compile(scope);
    stmtList.compile(scope);
    this.hasReturned = stmtList.hasReturned;
  }
}
