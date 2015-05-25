package ast;
import symbol.*;

public class Identifier extends Node {
  private String id;
  private boolean isArray;
  private IntNum n;

  public Identifier(Pos pos, String id) {
    this(pos, id, false, null);
  }
  public Identifier(Pos pos, String id, IntNum n) {
    this(pos, id, true, n);
  }
  public Identifier(Pos pos, String id, boolean isArray, IntNum n) {
    super(pos);
    this.id = id;
    this.isArray = isArray;
    this.n = n;
  }
  public boolean isArray() {
    return isArray;
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
