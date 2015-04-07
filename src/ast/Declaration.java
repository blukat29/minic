package ast;
public class Declaration extends Node {
  private TypeInfo ty;
  public Declaration(TypeInfo ty) {
    this.ty = ty;
  }
  public String toString() {
    return "Declares " + ty;
  }
}
