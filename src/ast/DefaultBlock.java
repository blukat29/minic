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
    indent(n); tree("DefaultBlock\n");
    stmtList.dumpAST(n + 1);
    if (hasBreak) {
      indent(n + 1); tree("break;\n");
    }
  }

  public void analyse(Scope scope) {
    stmtList.analyse(scope);
  }

  public void codegen(String exitLabel) {
    stmtList.codegen();
    if (hasBreak)
      code("JMP " + exitLabel);
  }
}
