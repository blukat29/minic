package ast;
import symbol.*;

public class Identifier extends Node {
  private IdExpr id;
  private boolean isArray;
  private IntNum n;

  public Identifier(IdExpr id) {
    this.id = id;
    isArray = false;
  }
  public Identifier(IdExpr id, IntNum n) {
    this.id = id;
    isArray = true;
    this.n = n;
  }

  public void dumpAST(int indent) {
    id.dumpAST(indent);
    if (isArray) {
      ASTWriter.write("[");
      n.dumpAST(0);
      ASTWriter.write("]");
    }
  }

  public String getName() {
    return id.getName();
  }

  public Symbol toSymbol(Scope scope, TypeInfo ty) {
    if (!isArray)
      return new Symbol(scope, ty, id.getName());
    else
      return new Symbol(scope, ty, id.getName(), n.getValue());
  }
}
