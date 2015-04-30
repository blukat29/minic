package ast;
import java.util.*;
import symbol.*;

public class Declaration extends Node {
  private TypeInfo ty;
  private List<Identifier> identList;

  public Declaration(Pos pos, TypeInfo ty, List<Identifier> il) {
    super(pos);
    this.ty = ty;
    this.identList = il;
  }

  public void dumpAST(int n) {
    ty.dumpAST(n);

    identList.get(0).dumpAST(0);
    for (int i=1; i<identList.size(); i++) {
      tree(", "); identList.get(i).dumpAST(0);
    }
    tree(";\n");
  }

  public void compile(Scope scope) {
    for (Identifier id : identList) {
      Symbol symbol = id.toSymbol(scope, ty, false);
      if (SymbolTable.lookup(scope, id.getName()) != null) {
        error(String.format("variable '%s' is already declared.", id.getName()), id);
      }
      else {
        SymbolTable.addSymbol(symbol);
      }
    }
  }
}
