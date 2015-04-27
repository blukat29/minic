package ast;
import java.util.*;
public class CaseList extends Node {
  private List<CaseBlock> caseBlocks;
  private DefaultBlock defaultBlock;
  public CaseList(List<CaseBlock> caseBlocks) {
    this(caseBlocks, null);
  }
  public CaseList(List<CaseBlock> caseBlocks, DefaultBlock defaultBlock) {
    this.caseBlocks = caseBlocks;
    this.defaultBlock = defaultBlock;
  }
  public String toString() {
    return caseBlocks + "\n" + defaultBlock;
  }
}
