package ast;
public class RetStmt extends Stmt {
  private Expr expr;

  public RetStmt() {
    this(null);
  }
  public RetStmt(Expr expr) {
    this.expr = expr;
  }

  public void dumpAST(int indent) {
    if (expr == null)
      ASTWriter.write("return;\n", indent);
    else {
      ASTWriter.write("return ", indent);
      expr.dumpAST(0);
      ASTWriter.write(";\n");
    }
  }
}
