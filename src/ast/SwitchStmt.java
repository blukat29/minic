package ast;
public class SwitchStmt extends Stmt {
  private Identifier id;
  private CaseList caseList;

  public SwitchStmt(Identifier id, CaseList caseList) {
    this.id = id;
    this.caseList = caseList;
  }

  public void dumpAST(int indent) {
    ASTWriter.write("switch ( ", indent);
    id.dumpAST(0);
    ASTWriter.write(" )\n");
    ASTWriter.write("{\n", indent);
    caseList.dumpAST(indent + 1);
    ASTWriter.write("}\n", indent);
  }
}
