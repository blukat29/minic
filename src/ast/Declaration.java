package ast;
import java.util.*;
public class Declaration extends Node {
  private TypeInfo ty;
  private List<Identifier> identList;
  public Declaration(TypeInfo ty, List<Identifier> il) {
    this.ty = ty;
    this.identList = il;
  }
  public String toString() {
    return "Declares " + ty + " " + identList;
  }
}
