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
    indent(n); tree("Declaration " + ty.toString() + "\n");
    for (Identifier id : identList)
      id.dumpAST(n+1);
  }

  public void analyse(Scope scope) {
    for (Identifier id : identList) {
      Symbol symbol = id.toSymbol(scope, ty, false);
      if (SymbolTable.lookupInSameScope(scope, id.getName()) != null) {
        error(String.format("variable '%s' is already declared.", id.getName()), id);
      }
      else {
        SymbolTable.addSymbol(symbol);
        if (scope.getFunction() == null) { /* Global scope. */
          int offset = scope.nextGlobalOffset(id.getSize());
          symbol.setOffset(offset);
        }
        else {
          Function func = scope.getFunction();
          int offset = func.nextStackOffset(id.getSize());
          symbol.setOffset(offset);
        }
      }
    }
  }
}
