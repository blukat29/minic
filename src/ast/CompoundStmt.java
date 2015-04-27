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

  public void dumpAST(int indent) {
    ASTWriter.write("{\n", indent);
    if (declList != null)
      declList.dumpAST(indent + 1);
    stmtList.dumpAST(indent + 1);
    ASTWriter.write("}\n", indent);
  }

  public void compile(Scope scope) {
    scope.push(this);
    if (declList != null)
      declList.compile(scope);
    scope.pop();
  }
}
