package ast;
public class DefaultBlock extends Node {
  private StmtList stmtList;
  private boolean hasBreak;
  public DefaultBlock(StmtList stmtList, boolean hasBreak) {
    this.stmtList = stmtList;
    this.hasBreak = hasBreak;
  }
  public String toString() {
    String s = "default:\n" + stmtList;
    if (hasBreak)
      s += "\nbreak;";
    return s;
  }
}
