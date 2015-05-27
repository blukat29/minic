package ast;
import symbol.*;

abstract public class Expr extends Node {
  protected TypeInfo ty = null;
  protected boolean isArray = false;
  public Expr() {
  }
  public Expr(Pos pos) {
    super(pos);
  }
  public abstract void analyse(Scope scope);
}
