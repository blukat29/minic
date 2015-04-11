package ast;
public class ArrayIndexExpr extends Expr {
  private IdExpr id;
  private Expr idx;
  public ArrayIndexExpr(IdExpr id, Expr idx) {
    this.id = id;
    this.idx = idx;
  }
  public String toString() {
    return String.format("%s[%s]", id, idx);
  }
}
