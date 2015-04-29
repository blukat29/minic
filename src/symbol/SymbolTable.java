package symbol;
import ast.Function;
import java.io.*;
import java.util.*;

public class SymbolTable {
  private static SymbolTable singleton = new SymbolTable();
  List<Symbol> table;
  List<Function> functions;

  private SymbolTable() {
    table = new ArrayList<Symbol>();
    functions = new ArrayList<Function>();
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

  public static void addFunction(Function func) {
    List<Function>functions = SymbolTable.getInstance().functions;
    functions.add(func);
  }

  public static Function lookupFunction(String name) {
    List<Function>functions = SymbolTable.getInstance().functions;
    for (Function func : functions) {
      if (func.getName().equals(name))
        return func;
    }
    return null;
  }

  public static void dumpTable(Writer writer) throws IOException {
    List<Symbol>table = SymbolTable.getInstance().table;
    List<Function>functions = SymbolTable.getInstance().functions;
    writer.write("# Variables\n");
    for (Symbol symbol : table) {
      writer.write(symbol + "\n");
    }
    writer.write("# Functions\n");
    for (Function func : functions) {
      writer.write(func + "\n");
    }
  }
}
