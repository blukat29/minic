package ast;
import symbol.*;

public class Function extends Node {
  private TypeInfo retTy;
  private String name;
  private ParamList params;
  private CompoundStmt body;

  public Function(Pos pos, TypeInfo retTy, String name, CompoundStmt body) {
    this(pos, retTy, name, null, body);
  }
  public Function(Pos pos, TypeInfo retTy, String name, ParamList params, CompoundStmt body) {
    super(pos);
    this.retTy = retTy;
    this.name = name;
    this.params = params;
    this.body = body;
  }
  public String getName() {
    return name;
  }

  public void dumpAST(int n) {
    retTy.dumpAST(n); tree(name + " ");
    if (params != null)
      params.dumpAST(0);
    else
      tree("(void)");
    tree("\n");
    body.dumpAST(n);
  }

  public void compile(Scope scope) {

    if (SymbolTable.lookupFunction(name) != null) {
      error(String.format("function '%s' is already defined.", name));
      return;
    }
    SymbolTable.addFunction(this);

    scope.push(this);
    if (params != null)
      params.compile(scope);
    body.compile(scope);
    scope.pop();
  }

  public String toString() {
    return name;
  }
}
