package ast;
public class ArrayIndexExpr extends Expr {
  private String id;
  private Expr idx;

  public ArrayIndexExpr(String id, Expr idx) {
    this.id = id;
    this.idx = idx;
  }

  public void dumpAST(int indent) {
    ASTWriter.write(id + "[", indent);
    idx.dumpAST(0);
    ASTWriter.write("]");
  }
}
