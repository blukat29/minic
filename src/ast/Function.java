package ast;
import symbol.*;

public class Function extends Node {
  private TypeInfo retTy;
  private String name;
  private ParamList params;
  private CompoundStmt body;

  public Function(TypeInfo retTy, String name, CompoundStmt body) {
    this(retTy, name, null, body);
  }
  public Function(TypeInfo retTy, String name, ParamList params, CompoundStmt body) {
    this.retTy = retTy;
    this.name = name;
    this.params = params;
    this.body = body;
  }
  public String getName() {
    return name;
  }

  public void dumpAST(int indent) {
    retTy.dumpAST(indent);
    ASTWriter.write(name + " ", indent);
    if (params != null)
      params.dumpAST(indent);
    else
      ASTWriter.write("(void)");
    ASTWriter.write("\n");
    body.dumpAST(indent);
  }

  public void compile(Scope scope) {

    if (SymbolTable.lookupFunction(name) != null) {
      ErrorWriter.error(String.format("function '%s' is already defined.", name));
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
