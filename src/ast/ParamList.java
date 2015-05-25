package ast;
import java.util.*;
import symbol.*;

public class ParamList extends Node {
  private List<TypeInfo> tyList;
  private List<Identifier> idList;

  public ParamList() {
    tyList = new ArrayList<TypeInfo>();
    idList = new ArrayList<Identifier>();
  }
  public ParamList append(TypeInfo ty, Identifier id) {
    tyList.add(ty);
    idList.add(id);
    return this;
  }
  public int size() {
    return tyList.size();
  }
  public List<TypeInfo> getTyList() {
    return tyList;
  }
  public List<Identifier> getIdList() {
    return idList;
  }

  public void dumpAST(int n) {
    tree("(");
    if (tyList.size() > 0) {
      tyList.get(0).dumpAST(0);
      idList.get(0).dumpAST(0);
    }
    for (int i=1; i<tyList.size(); i++) {
      tree(", ");
      tyList.get(i).dumpAST(0);
      idList.get(i).dumpAST(0);
    }
    tree(")");
  }

  public void compile(Scope scope) {
    for (int i=0; i<tyList.size(); i++) {
      TypeInfo ty = tyList.get(i);
      Identifier id = idList.get(i);
      if (SymbolTable.lookupInSameScope(scope, id.getName()) != null) {
        error(String.format("variable '%s' is already declared.", id.getName()), id);
      }
      else {
        Symbol symbol = id.toSymbol(scope, ty, true);
        SymbolTable.addSymbol(symbol);
      }
    }
  }
}
