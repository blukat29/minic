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

  public void dumpAST(int indent) {
    if (idx == null) {
      ASTWriter.write(id + " = ", indent);
      val.dumpAST(0);
    }
    else {
      ASTWriter.write(id + "[", indent);
      idx.dumpAST(0);
      ASTWriter.write("] = ");
      val.dumpAST(0);
    }
  }

  public void compile(Scope scope) {

    Symbol destSymbol = SymbolTable.lookup(scope, id);
    if (destSymbol == null) {
      ErrorWriter.error(String.format("variable '%s' is not defined.", id));
      return;
    }
    if (idx != null && !destSymbol.isArray()) {
      ErrorWriter.error(String.format("variable '%s' is not an array.", id));
      return;
    }
    if (idx == null && destSymbol.isArray()) {
      ErrorWriter.error(String.format("variable '%s' is an array.", id));
      return;
    }
  }
}
