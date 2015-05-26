package ast;
import symbol.*;

public class IdExpr extends Expr {
  private String name;

  public IdExpr(Pos pos, String name) {
    super(pos);
    this.name = name;
  }
  public String getName() {
    return name;
  }

  public void dumpAST(int n) {
    indent(n); tree("ID:" + name + "\n");
  }

  public void compile(Scope scope) {
    Symbol destSymbol = SymbolTable.lookup(scope, name);
    if (destSymbol == null) {
      error(String.format("variable '%s' is not defined.", name));
      return;
    }
    this.isArray = destSymbol.isArray();
    this.ty = destSymbol.getType();
  }
  public String toString() {
    return name;
  }
}
