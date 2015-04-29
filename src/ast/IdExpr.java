package ast;
import symbol.*;

public class IdExpr extends Expr {
  private String name;
  public IdExpr(String name) {
    this.name = name;
  }
  public String getName() {
    return name;
  }
  public void dumpAST(int indent) {
    ASTWriter.write(name, indent);
  }
  public void compile(Scope scope) {
    Symbol destSymbol = SymbolTable.lookup(scope, name);
    if (destSymbol == null) {
      ErrorWriter.error(String.format("variable '%s' is not defined.", name));
      return;
    }
    if (destSymbol.isArray()) {
      ErrorWriter.error(String.format("variable '%s' is an array.", name));
      return;
    }
  }
}
