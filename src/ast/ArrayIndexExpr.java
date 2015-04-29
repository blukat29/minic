package ast;
import symbol.*;

public class ArrayIndexExpr extends Expr {
  private String id;
  private Expr idx;

  public ArrayIndexExpr(String id, Expr idx) {
    this.id = id;
    this.idx = idx;
  }

  public void dumpAST(int indent) {
    ASTWriter.write(id + "[", indent);
    idx.dumpAST(0);
    ASTWriter.write("]");
  }

  public void compile(Scope scope) {
    Symbol destSymbol = SymbolTable.lookup(scope, id);
    if (destSymbol == null) {
      ErrorWriter.error(String.format("variable '%s' is not defined.", id));
      return;
    }
    if (!destSymbol.isArray()) {
      ErrorWriter.error(String.format("variable '%s' is not an array.", id));
      return;
    }
    idx.compile(scope);
  }
}
