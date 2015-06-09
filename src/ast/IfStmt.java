package ast;
import symbol.*;

public class IfStmt extends Stmt {
  private Expr cond;
  private Stmt thenStmt;
  private Stmt elseStmt;
  private static int nextLabel = 0;

  public IfStmt(Expr c, Stmt t) {
    this(c, t, null);
  }
  public IfStmt(Expr c, Stmt t, Stmt e) {
    cond = c;
    thenStmt = t;
    elseStmt = e;
  }

  public void dumpAST(int n) {
    indent(n); tree("IfStmt\n");
    cond.dumpAST(n+1);
    thenStmt.dumpAST(n+1);
    if (elseStmt != null)
      elseStmt.dumpAST(n+1);
  }

  public void analyse(Scope scope) {
    cond.analyse(scope);
    thenStmt.analyse(scope);
    if (elseStmt != null)
      elseStmt.analyse(scope);
    if (cond.isArray) {
      error("An array cannot be boolean", cond);
      return;
    }
    if (elseStmt == null && thenStmt.hasReturned)
      this.hasReturned = true;
    if (elseStmt != null && thenStmt.hasReturned && elseStmt.hasReturned)
      this.hasReturned = true;
  }

  public void codegen() {
    /* if (cond == 0) jmp if_else
           ThenStmt
           jmp if_done
       if_else:
           ElseStmt
       if_done:
     */
    int labelIdx = ++nextLabel;
    boolean hasElse = (elseStmt != null);

    cond.codegen();
    if (hasElse)
      code(String.format("JMPZ VR(%d)@ if_else_%d", cond.reg, labelIdx));
    else
      code(String.format("JMPZ VR(%d)@ if_done_%d", cond.reg, labelIdx));

    thenStmt.codegen();
    if (hasElse) {
      code(String.format("JMP if_done_%d", labelIdx));
      code(String.format("LAB if_else_%d", labelIdx));
      elseStmt.codegen();
    }
    code(String.format("LAB if_done_%d", labelIdx));
  }
}
