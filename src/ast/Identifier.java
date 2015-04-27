package ast;
import symbol.*;

public class Identifier extends Node {
  private IdExpr id;
  private boolean isArray;
  private IntNum n;
  public Identifier(IdExpr id) {
    this.id = id;
    isArray = false;
  }
  public Identifier(IdExpr id, IntNum n) {
    this.id = id;
    isArray = true;
    this.n = n;
  }
  public String toString() {
    String s = id.toString();
    if (isArray)
      s += "[" + n + "]";
    return s;
  }
  public Symbol toSymbol(TypeInfo ty) {
    if (!isArray)
      return new Symbol(ty, id.getName());
    else
      return new Symbol(ty, id.getName(), n.getValue());
  }
}
