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
  public TypeInfo getRetTy() {
    return retTy;
  }
  public ParamList getParams() {
    return params;
  }

  public void dumpAST(int n) {
    indent(n); tree("Function " + retTy + " " + name + "\n");
    if (params != null) {
      params.dumpAST(n+1);
    } else {
      indent(n+1);
      tree("(void)\n");
    }
    body.dumpAST(n+1);
  }

  public void analyse(Scope scope) {

    if (SymbolTable.lookupFunction(name) != null) {
      error(String.format("function '%s' is already defined.", name));
      return;
    }
    SymbolTable.addFunction(this);

    scope.push(this);
    if (params != null)
      params.analyse(scope);
    body.analyseFunctionBlock(scope);
    scope.pop();
    if (!body.hasReturned) {
      error(String.format("Function '%s' did not return a value", name), this);
      return;
    }
  }

  public String toString() {
    return name;
  }
}
