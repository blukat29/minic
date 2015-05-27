package ast;
import symbol.*;

public class CaseBlock extends Node {
  private IntNum num;
  private StmtList stmtList;
  private boolean hasBreak;

  public CaseBlock(IntNum num, StmtList stmtList, boolean hasBreak) {
    this.num = num;
    this.stmtList = stmtList;
    this.hasBreak = hasBreak;
  }

  public void dumpAST(int n) {
    indent(n); tree("CaseBlock (" + num + ")\n");
    stmtList.dumpAST(n + 1);
    if (hasBreak) {
      indent(n + 1); tree("break;\n");
    }
  }

  public void analyse(Scope scope) {
    num.analyse(scope);
    stmtList.analyse(scope);
  }
}
