package ast;
public class SwitchStmt extends Stmt {
  private Identifier id;
  private CaseList caseList;
  public SwitchStmt(Identifier id, CaseList caseList) {
    this.id = id;
    this.caseList = caseList;
  }
  public String toString() {
    return String.format("switch( %s ) {\n%s }", id, caseList);
  }
}
