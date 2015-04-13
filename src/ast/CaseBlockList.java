package ast;
import java.util.*;
public class CaseBlockList extends Node {
  private List<CaseBlock> blocks;
  public CaseBlockList() {
    blocks = new ArrayList<CaseBlock>();
  }
  public CaseBlockList append(CaseBlock block) {
    blocks.add(block);
    return this;
  }
  public String toString() {
    String s = "CaseBlockList[";
    for (CaseBlock block : blocks)
      s += "\n  " + block;
    s += "\n]";
    return s;
  }
}
