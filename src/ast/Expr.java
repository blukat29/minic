package ast;
import symbol.*;

abstract public class Expr extends Node {
  public abstract void compile(Scope scope);
}
