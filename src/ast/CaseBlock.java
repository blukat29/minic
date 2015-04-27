package ast;
public class CaseBlock extends Node {
  private IntNum num;
  private StmtList stmtList;
  private boolean hasBreak;
  public CaseBlock(IntNum num, StmtList stmtList, boolean hasBreak) {
    this.num = num;
    this.stmtList = stmtList;
    this.hasBreak = hasBreak;
  }
  public void dumpAST(int indent) {
    ASTWriter.write("case ", indent);
    num.dumpAST(0);
    ASTWriter.write(":\n");
    stmtList.dumpAST(indent + 1);
    if (hasBreak)
      ASTWriter.write("break;\n", indent + 1);
  }
}
