package ast;
import symbol.*;

public class SwitchStmt extends Stmt {
  private Expr value;
  private CaseList caseList;

  public SwitchStmt(Expr value, CaseList caseList) {
    this.value = value;
    this.caseList = caseList;
  }

  public void dumpAST(int indent) {
    ASTWriter.write("switch ( ", indent);
    value.dumpAST(0);
    ASTWriter.write(" )\n");
    ASTWriter.write("{\n", indent);
    caseList.dumpAST(indent + 1);
    ASTWriter.write("}\n", indent);
  }

  public void compile(Scope scope) {
  }
}
