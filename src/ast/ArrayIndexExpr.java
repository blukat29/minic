package ast;
public class ArrayIndexExpr extends Expr {
  private IdExpr id;
  private Expr idx;

  public ArrayIndexExpr(IdExpr id, Expr idx) {
    this.id = id;
    this.idx = idx;
  }

  public void dumpAST(int indent) {
    id.dumpAST(indent);
    ASTWriter.write("[");
    idx.dumpAST(0);
    ASTWriter.write("]");
  }
}
