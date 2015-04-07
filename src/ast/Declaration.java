package ast;
public class Declaration extends Node {
  private TypeInfo ty;
  private IdentList identList;
  public Declaration(TypeInfo ty, IdentList il) {
    this.ty = ty;
    this.identList = il;
  }
  public String toString() {
    return "Declares " + ty + " " + identList;
  }
}
