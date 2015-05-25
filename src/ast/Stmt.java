package ast;
import symbol.*;

abstract public class Stmt extends Node {
  protected boolean hasReturned = false;
  public abstract void compile(Scope scope);
}
