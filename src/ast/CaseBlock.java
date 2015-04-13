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
  public String toString() {
    String s = String.format("case %s:\n", num) + stmtList;
    if (hasBreak)
      s += "\nbreak;";
    return s;
  }
}
