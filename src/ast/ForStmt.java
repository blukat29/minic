package ast;
public class ForStmt extends Stmt {
  private Assign init;
  private Expr cond;
  private Assign incr;
  private Stmt body;

  public ForStmt(Assign init, Expr cond, Assign incr, Stmt body) {
    this.init = init;
    this.cond = cond;
    this.incr = incr;
    this.body = body;
  }

  public void dumpAST(int indent) {
    ASTWriter.write("for ( ", indent);
    init.dumpAST(0);
    ASTWriter.write(" ; ");
    ASTWriter.write("expr");
    ASTWriter.write(" ; ");
    incr.dumpAST(0);
    ASTWriter.write(" )\n");
    ASTWriter.write("{\n", indent);
    body.dumpAST(indent + 1);
    ASTWriter.write("}\n", indent);
  }
}
