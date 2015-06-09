package ast;
import symbol.*;

public class IdExpr extends Expr {
  private String name;
  private Symbol symbol;

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

  public void analyse(Scope scope) {
    Symbol destSymbol = SymbolTable.lookup(scope, name);
    if (destSymbol == null) {
      error(String.format("variable '%s' is not defined.", name));
      return;
    }
    this.isArray = destSymbol.isArray();
    this.ty = destSymbol.getType();
    this.symbol = destSymbol;
  }

  public String toString() {
    return name;
  }

  public void codegen() {
    int offset = symbol.getOffset();
    this.reg = nextReg();
    if (symbol.isGlobal())
      code(String.format("MOVE MEM(%d)@ VR(%d)", offset, reg));
    else
      code(String.format("MOVE MEM(FP@(%d))@ VR(%d)", offset, reg));
  }
}
