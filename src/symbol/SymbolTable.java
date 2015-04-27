package symbol;
import java.util.*;
public class SymbolTable {
  private static SymbolTable singleton = new SymbolTable();
  List<Symbol> table;

  private SymbolTable() {
    table = new ArrayList<Symbol>();
  }

  public static SymbolTable getInstance() {
    return singleton;
  }

  public void addSymbol(Symbol symbol) {
    table.add(symbol);
  }

  public Symbol lookup(Scope scope, String name) {
    for (Symbol symbol : table) {
      if (symbol.getName().equals(name)) {
        if (symbol.getScope().isParentOf(scope))
          return symbol;
      }
    }
    return null;
  }

  public String toString() {
    return table.toString();
  }
}
