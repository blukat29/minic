package ast;
import symbol.*;

public class Identifier extends Node {
  private String id;
  private boolean isArray;
  private IntNum n;

  public Identifier(String id) {
    this.id = id;
    isArray = false;
  }
  public Identifier(String id, IntNum n) {
    this.id = id;
    isArray = true;
    this.n = n;
  }

  public void dumpAST(int n) {
    indent(n); tree(id);
    if (isArray) {
      tree("["); this.n.dumpAST(0); tree("]");
    }
  }

  public String getName() {
    return id;
  }

  public Symbol toSymbol(Scope scope, TypeInfo ty, boolean isParam) {
    if (!isArray)
      return new Symbol(scope, ty, id, isParam);
    else
      return new Symbol(scope, ty, id, isParam, n.getValue());
  }
}
