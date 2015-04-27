package ast;
import symbol.*;

public class Function extends Node {
  private TypeInfo retTy;
  private IdExpr name;
  private ParamList params;
  private CompoundStmt body;
  public Function(TypeInfo retTy, IdExpr name, CompoundStmt body) {
    this(retTy, name, null, body);
  }
  public Function(TypeInfo retTy, IdExpr name, ParamList params, CompoundStmt body) {
    this.retTy = retTy;
    this.name = name;
    this.params = params;
    this.body = body;
  }
  public String toString() {
    return "function " + retTy + " " + name + " " + params + "\n" + body;
  }
  public String getName() {
    return name.getName();
  }

  public void compile(Scope scope) {
    scope.push(this);
    body.compile(scope);
    scope.pop();
  }
}
