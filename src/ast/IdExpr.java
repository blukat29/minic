package ast;
public class IdExpr extends Expr {
  private String name;
  public IdExpr(String name) {
    this.name = name;
  }
  public String getName() {
    return name;
  }
  public String toString() {
    return name;
  }
}
