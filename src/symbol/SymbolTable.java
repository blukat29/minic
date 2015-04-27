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
  public String toString() {
    return table.toString();
  }
}
