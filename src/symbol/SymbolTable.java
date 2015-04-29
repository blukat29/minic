package symbol;
import java.io.*;
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

  public static void addSymbol(Symbol symbol) {
    List<Symbol>table = SymbolTable.getInstance().table;
    table.add(symbol);
  }

  public static Symbol lookup(Scope scope, String name) {
    List<Symbol>table = SymbolTable.getInstance().table;
    for (Symbol symbol : table) {
      if (symbol.getName().equals(name)) {
        if (symbol.getScope().isParentOf(scope))
          return symbol;
      }
    }
    return null;
  }

  public static void dumpTable(Writer writer) throws IOException {
    List<Symbol>table = SymbolTable.getInstance().table;
    for (Symbol symbol : table) {
      writer.write(symbol.toString());
      writer.write("\n");
    }
  }
}
