package ast;
import java.util.*;
import symbol.*;

public class Declaration extends Node {
  private TypeInfo ty;
  private List<Identifier> identList;
  public Declaration(TypeInfo ty, List<Identifier> il) {
    this.ty = ty;
    this.identList = il;
  }
  public String toString() {
    return "declare " + ty + " " + identList + ";\n";
  }

  public void compile(Scope scope) {
    SymbolTable table = SymbolTable.getInstance();
    for (Identifier id : identList) {
      table.addSymbol(id.toSymbol(scope, ty));
    }
  }
}
