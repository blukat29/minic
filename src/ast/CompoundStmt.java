package ast;
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
    return declList + "\n" + stmtList;
  }
}
