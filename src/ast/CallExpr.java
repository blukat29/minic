package ast;
public class CallExpr extends Expr {
  private IdExpr id;
  private ArgList args;
  public CallExpr(IdExpr id) {
    this(id, null);
  }
  public CallExpr(IdExpr id, ArgList args) {
    this.id = id;
    this.args = args;
  }
  public String toString() {
    return String.format("call %s(%s)", id, args);
  }
}


