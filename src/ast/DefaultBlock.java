package ast;
import symbol.*;

public class DefaultBlock extends Node {
  private StmtList stmtList;
  private boolean hasBreak;

  public DefaultBlock(StmtList stmtList, boolean hasBreak) {
    this.stmtList = stmtList;
    this.hasBreak = hasBreak;
  }

  public void dumpAST(int n) {
    indent(n); tree("default:\n");
    stmtList.dumpAST(n + 1);
    if (hasBreak) {
      indent(n + 1); tree("break;\n");
    }
  }

  public void compile(Scope scope) {
    stmtList.compile(scope);
  }
}
