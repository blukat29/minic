package ast;
public class Function extends Node {
  private TypeInfo retTy;
  private IdExpr name;
  private ParamList params;
  public Function(TypeInfo retTy, IdExpr name) {
    this(retTy, name, null);
  }
  public Function(TypeInfo retTy, IdExpr name, ParamList params) {
    this.retTy = retTy;
    this.name = name;
    this.params = params;
  }
  public String toString() {
    return "Function " + retTy + " " + name + " " + params;
  }
}
