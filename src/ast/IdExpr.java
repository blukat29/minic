package ast;
public class IdExpr extends Expr {
  String name;
  public IdExpr(String name) {
    this.name = name;
  }
  public String toString() {
    return "id:" + name;
  }
}
