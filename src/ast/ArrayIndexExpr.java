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
    // MEM(FP + offset + VR(idxReg))
    int offset = symbol.getOffset();
    int addr = nextReg();
    int dst = nextReg();
    idx.codegen();
    code(String.format("ADD FP@ VR(%d)@ VR(%d)", idx.reg, addr));
    code(String.format("ADD VR(%d)@ %d VR(%d)", addr, offset, addr));
    code(String.format("MOVE MEM(VR(%d)@)@ VR(%d)", addr, dst));
    this.reg = dst;
  }
}
