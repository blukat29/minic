package ast;
import java.util.*;
import symbol.*;

public class CaseList extends Node {
  private List<CaseBlock> caseBlocks;
  private DefaultBlock defaultBlock;
  private static int nextLabel = 0;

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

  public void codegen(int inputReg) {

    /* For some reason, T-machine does not accept
     * the label name with two separate numbers like
     * switch_1_100. So we use switch_A_100 */
    char labelIdx = (char)('A' + nextLabel++);
    String exitLabel = String.format("switch_%c_end", labelIdx);
    String defaultLabel = String.format("switch_%c_default", labelIdx);

    for (CaseBlock block : caseBlocks) {
      int num = block.getValue();
      code(String.format("SUB VR(%d)@ %d VR(0)", inputReg, num));
      code(String.format("JMPZ VR(0)@ switch_%c_%d", labelIdx, num));
    }
    if (defaultBlock != null)
      code("JMP " + defaultLabel);
    else
      code("JMP " + exitLabel);

    for (CaseBlock block : caseBlocks) {
      int num = block.getValue();
      code(String.format("LAB switch_%c_%d", labelIdx, num));
      block.codegen(exitLabel);
    }
    if (defaultBlock != null) {
      code("LAB " + defaultLabel);
      defaultBlock.codegen(exitLabel);
    }
    code("LAB " + exitLabel);
  }
}
