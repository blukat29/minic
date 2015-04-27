package ast;
import java.util.*;
import symbol.*;

public class Declaration extends Node {
  private TypeInfo ty;
  private List<Identifier> identList;
  public Declaration(TypeInfo ty, List<Identifier> il) {
    this.ty = ty;
    this.identList = il;
    registerSymbols();
  }
  public String toString() {
    return "declare " + ty + " " + identList + ";\n";
  }
  private void registerSymbols() {
    SymbolTable table = SymbolTable.getInstance();
    for (Identifier id : identList) {
      table.addSymbol(id.toSymbol(ty));
    }
  }
}
