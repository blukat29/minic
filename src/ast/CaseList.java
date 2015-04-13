package ast;
public class CaseList extends Node {
  private CaseBlockList caseBlocks;
  private DefaultBlock defaultBlock;
  public CaseList(CaseBlockList caseBlocks) {
    this(caseBlocks, null);
  }
  public CaseList(CaseBlockList caseBlocks, DefaultBlock defaultBlock) {
    this.caseBlocks = caseBlocks;
    this.defaultBlock = defaultBlock;
  }
  public String toString() {
    return caseBlocks + "\n" + defaultBlock;
  }
}
