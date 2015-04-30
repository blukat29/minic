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
    indent(n); tree(name);
  }

  public void compile(Scope scope) {
    Symbol destSymbol = SymbolTable.lookup(scope, name);
    if (destSymbol == null) {
      error(String.format("variable '%s' is not defined.", name));
      return;
    }
    if (destSymbol.isArray()) {
      error(String.format("variable '%s' is an array.", name));
      return;
    }
  }
}
