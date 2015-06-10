package ast;
import symbol.*;

public class ArrayIndexExpr extends Expr {
  private String id;
  private Expr idx;
  private Symbol symbol;

  public ArrayIndexExpr(Pos pos, String id, Expr idx) {
    super(pos);
    this.id = id;
    this.idx = idx;
  }
  public String getId() {
    return this.id;
  }
  public Expr getIdx() {
    return this.idx;
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
    this.symbol = destSymbol;
    this.ty = destSymbol.getType();
  }

  public void codegen() {
    int dst = nextReg();
    idx.codegen();
    String src = getMemoryAccess(this.symbol, idx.reg);
    code(String.format("MOVE %s@ VR(%d)", src, dst));
    this.reg = dst;
  }
}
