package ast;
import symbol.*;

public class Assign extends Node {
  private String id;
  private Expr idx;
  private Expr val;

  public Assign(String id, Expr val) {
    this(id, null, val);
  }
  public Assign(String id, Expr idx, Expr val) {
    this.id = id;
    this.idx = idx;
    this.val = val;
  }

  public void dumpAST(int n) {
    if (idx == null) {
      indent(n); tree(id + " = "); val.dumpAST(0);
    }
    else {
      indent(n); tree(id + "["); idx.dumpAST(0); tree("]"); val.dumpAST(0);
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
  }
}
