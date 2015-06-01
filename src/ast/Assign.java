package ast;
import symbol.*;

public class Assign extends Node {
  private String id;
  private Expr idx;
  private Expr val;
  Symbol symbol;

  public Assign(Pos pos, String id, Expr val) {
    this(pos, id, null, val);
  }
  public Assign(Pos pos, String id, Expr idx, Expr val) {
    super(pos);
    this.id = id;
    this.idx = idx;
    this.val = val;
  }

  public void dumpAST(int n) {
    indent(n); tree("Assign\n");
    indent(n+1); tree(id + "\n");
    if (idx != null)
      idx.dumpAST(n+1);
    val.dumpAST(n+1);
  }

  public void analyse(Scope scope) {

    Symbol destSymbol = SymbolTable.lookup(scope, id);
    if (destSymbol == null) {
      error(String.format("variable '%s' is not defined.", id));
      return;
    }
    if (idx != null && !destSymbol.isArray()) {
      error(String.format("variable '%s' is not an array.", id));
      return;
    }
    if (idx == null && destSymbol.isArray()) {
      error(String.format("variable '%s' is an array.", id));
      return;
    }
    if (idx != null) {
      idx.analyse(scope);
      if (idx.ty == null || !idx.ty.equals(new TypeInfo(TypeInfo.INT))) {
        error(String.format("Array index must be integer.", idx));
        return;
      }
    }
    val.analyse(scope);
    if (val.isArray) {
      error("Cannot assign an array.", val);
      return;
    }
    if (val.ty != null) {
      TypeInfo lhsTy = destSymbol.getType();
      TypeInfo rhsTy = val.ty;
      if (!lhsTy.equals(rhsTy)) {
        warn(String.format("Implicitly casting %s to %s", rhsTy, lhsTy), this);
        val = new TypeCast(lhsTy, val);
        val.analyse(scope);
      }
    }
    this.symbol = destSymbol;
  }

  public void codegen() {
    String dst;
    int offset = symbol.getOffset();
    if (idx != null) {
      idx.codegen();
      // dst = idx + FP + offset
      code(String.format("ADD VR(%d)@ FP@ VR(%d)", idx.reg, idx.reg));
      dst = String.format("MEM(VR(%d)@(%d))", idx.reg, offset);
    }
    else {
      // dst = FP + offset
      dst = String.format("MEM(FP@(%d))", offset);
    }
    val.codegen();
    code(String.format("MOVE VR(%d)@ %s", val.reg, dst));
  }
}
