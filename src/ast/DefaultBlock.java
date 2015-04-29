package ast;
import symbol.*;

public class DefaultBlock extends Node {
  private StmtList stmtList;
  private boolean hasBreak;

  public DefaultBlock(StmtList stmtList, boolean hasBreak) {
    this.stmtList = stmtList;
    this.hasBreak = hasBreak;
  }

  public void dumpAST(int indent) {
    ASTWriter.write("default:\n", indent);
    stmtList.dumpAST(indent + 1);
    if (hasBreak)
      ASTWriter.write("break;\n", indent + 1);
  }

  public void compile(Scope scope) {
    stmtList.compile(scope);
  }
}
