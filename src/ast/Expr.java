package ast;
import symbol.*;

abstract public class Expr extends Node {
  protected TypeInfo ty = null;
  public Expr() {
  }
  public Expr(Pos pos) {
    super(pos);
  }
  public abstract void compile(Scope scope);
}
