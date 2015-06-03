package ast;
import symbol.*;

public class ForStmt extends Stmt {
  private Assign init;
  private Expr cond;
  private Assign incr;
  private Stmt body;
  private static int nextLabel = 0;

  public ForStmt(Assign init, Expr cond, Assign incr, Stmt body) {
    this.init = init;
    this.cond = cond;
    this.incr = incr;
    this.body = body;
  }

  public void dumpAST(int n) {
    indent(n); tree("ForStmt\n");
    init.dumpAST(n+1);
    cond.dumpAST(n+1);
    incr.dumpAST(n+1);
    body.dumpAST(n+1);
  }

  public void analyse(Scope scope) {
    init.analyse(scope);
    cond.analyse(scope);
    incr.analyse(scope);
    body.analyse(scope);
    if (cond.isArray) {
      error("An array cannot be boolean", cond);
      return;
    }
  }

  public void codegen() {
    /*   init
     * for_start:
     *   cond == 0? jump for_end
     *   body
     *   incr
     *   jump for_start
     * for_end:
     */
    code("// ForStmt not impl.");
    int labelIdx = ++nextLabel;

    init.codegen();

    code(String.format("LAB for_start_%d", labelIdx));
    cond.codegen();
    code(String.format("JMPZ VR(%d)@ for_end_%d", cond.reg, labelIdx));
    body.codegen();
    incr.codegen();
    code(String.format("JMP for_start_%d", labelIdx));
    code(String.format("LAB for_end_%d", labelIdx));
  }
}
