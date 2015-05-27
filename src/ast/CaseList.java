package ast;
import java.util.*;
import symbol.*;

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

  public void dumpAST(int n) {
    for (CaseBlock caseBlock : caseBlocks)
      caseBlock.dumpAST(n);
    if (defaultBlock != null)
      defaultBlock.dumpAST(n);
  }

  public void analyse(Scope scope) {
    for (CaseBlock caseBlock : caseBlocks)
      caseBlock.analyse(scope);
    if (defaultBlock != null)
      defaultBlock.analyse(scope);
  }
}
