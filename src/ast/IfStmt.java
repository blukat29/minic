package ast;
import symbol.*;

public class IfStmt extends Stmt {
  private Expr cond;
  private Stmt thenStmt;
  private Stmt elseStmt;

  public IfStmt(Expr c, Stmt t) {
    this(c, t, null);
  }
  public IfStmt(Expr c, Stmt t, Stmt e) {
    cond = c;
    thenStmt = t;
    elseStmt = e;
  }

  public void dumpAST(int indent) {
    ASTWriter.write("if ( ", indent);
    cond.dumpAST(0);
    ASTWriter.write(" )\n");
    ASTWriter.write("{\n", indent);
    thenStmt.dumpAST(indent + 1);
    ASTWriter.write("}\n", indent);
    if (elseStmt != null) {
      ASTWriter.write("else\n", indent);
      ASTWriter.write("{\n", indent);
      elseStmt.dumpAST(indent + 1);
      ASTWriter.write("}\n", indent);
    }
  }

  public void compile(Scope scope) {
  }
}
