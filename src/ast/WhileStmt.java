package ast;
import symbol.*;

public class WhileStmt extends Stmt {
  private Expr cond;
  private Stmt body;
  private boolean isDoWhile;
  private static int nextLabel = 0;

  public WhileStmt(Expr cond, Stmt body, boolean isDoWhile) {
    this.cond = cond;
    this.body = body;
    this.isDoWhile = isDoWhile;
  }

  public void dumpAST(int n) {
    if (isDoWhile) {
      indent(n); tree("WhileStmt(do-while)\n");
      body.dumpAST(n+1);
      cond.dumpAST(n+1);
    }
    else {
      indent(n); tree("WhileStmt(while)\n");
      cond.dumpAST(n+1);
      body.dumpAST(n+1);
    }
  }

  public void analyse(Scope scope) {
    cond.analyse(scope);
    body.analyse(scope);
    if (cond.isArray) {
      error("An array cannot be boolean", cond);
      return;
    }
  }

  public void codegen() {
    /* if while-loop, jmp loop_cond
     * loop_start:
     *   body
     * loop_cond:
     *   cond == 0? jmp loop_end
     *   jmp loop_start
     * loop_end:
     */
    code("// WhileStmt");

    int labelIdx = ++nextLabel;
    if(!isDoWhile)
      code(String.format("JMP loop_cond_%d", labelIdx));

    code(String.format("LAB loop_start_%d", labelIdx));
    body.codegen();

    if (!isDoWhile)
      code(String.format("LAB loop_cond_%d", labelIdx));
    cond.codegen();
    code(String.format("JMPZ VR(%d)@ loop_end_%d", cond.reg, labelIdx));
    code(String.format("JMP loop_start_%d", labelIdx));
    code(String.format("LAB loop_end_%d", labelIdx));
  }
}
