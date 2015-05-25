package ast;
import symbol.*;

public class Assign extends Node {
  private String id;
  private Expr idx;
  private Expr val;

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
    if (idx == null) {
      indent(n); tree(id + " = "); val.dumpAST(0);
    }
    else {
      indent(n); tree(id + "["); idx.dumpAST(0); tree("] = "); val.dumpAST(0);
    }
  }

  public void compile(Scope scope) {

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
      idx.compile(scope);
      if (idx.ty == null || !idx.ty.equals(new TypeInfo(TypeInfo.INT))) {
        error(String.format("Array index must be integer.", idx));
        return;
      }
    }
    val.compile(scope);
    if (val.ty != null) {
      TypeInfo lhsTy = destSymbol.getType();
      TypeInfo rhsTy = val.ty;
      if (!lhsTy.equals(rhsTy)) {
        warn(String.format("Implicitly casting %s to %s", rhsTy, lhsTy), this);
        val = new TypeCast(lhsTy, val);
        val.compile(scope);
      }
    }
  }
}
