package ast;
import symbol.*;

abstract public class Expr extends Node {
  public Expr() {
    super();
  }
  public Expr(Pos pos) {
    super(pos);
  }
  public abstract void compile(Scope scope);
}
