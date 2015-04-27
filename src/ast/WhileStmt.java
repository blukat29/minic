package ast;
public class WhileStmt extends Stmt {
  private Expr cond;
  private Stmt body;
  private boolean isDoWhile;

  public WhileStmt(Expr cond, Stmt body, boolean isDoWhile) {
    this.cond = cond;
    this.body = body;
    this.isDoWhile = isDoWhile;
  }

  public void dumpAST(int indent) {
    if (isDoWhile) {
      ASTWriter.write("do\n", indent);
      ASTWriter.write("{\n", indent);
      body.dumpAST(indent + 1);
      ASTWriter.write("} while ( ", indent);
      cond.dumpAST(0);
      ASTWriter.write(" );\n");
    }
    else {
      ASTWriter.write("while ( ", indent);
      cond.dumpAST(0);
      ASTWriter.write(" )\n");
      ASTWriter.write("{\n", indent);
      body.dumpAST(indent + 1);
      ASTWriter.write("}\n", indent);
    }
  }
}
