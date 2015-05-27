package ast;
import symbol.*;

public class ArrayIndexExpr extends Expr {
  private String id;
  private Expr idx;

  public ArrayIndexExpr(Pos pos, String id, Expr idx) {
    super(pos);
    this.id = id;
    this.idx = idx;
  }

  public void dumpAST(int n) {
    indent(n); tree("ArrayIndexExpr\n");
    indent(n+1); tree(id + "\n");
    idx.dumpAST(n+1);
  }

  public void analyse(Scope scope) {
    Symbol destSymbol = SymbolTable.lookup(scope, id);
    if (destSymbol == null) {
      error(String.format("variable '%s' is not defined.", id));
      return;
    }
    if (!destSymbol.isArray()) {
      error(String.format("variable '%s' is not an array.", id));
      return;
    }
    idx.analyse(scope);
    this.ty = destSymbol.getType();
  }
}
