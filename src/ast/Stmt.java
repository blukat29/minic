package ast;
import symbol.*;

abstract public class Stmt extends Node {
  public abstract void compile(Scope scope);
}
