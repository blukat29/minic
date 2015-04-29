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

  public void dumpAST(int indent) {
    for (CaseBlock caseBlock : caseBlocks)
      caseBlock.dumpAST(indent);
    if (defaultBlock != null)
      defaultBlock.dumpAST(indent);
  }

  public void compile(Scope scope) {
    for (CaseBlock caseBlock : caseBlocks)
      caseBlock.compile(scope);
    if (defaultBlock != null)
      defaultBlock.compile(scope);
  }
}
