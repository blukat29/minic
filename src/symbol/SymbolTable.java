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

  public static Symbol lookup(Scope scope_, String name) {
    List<Symbol>table = SymbolTable.getInstance().table;
    Scope scope = scope_.clone();
    while (true) {
      Symbol result = lookupInSameScope(scope, name);
      if (result != null)
        return result;
      if (!scope.isEmpty())
        scope.pop();
      else
        break;
    }
    return null;
  }

  public static Symbol lookupInSameScope (Scope scope, String name) {
    List<Symbol>table = SymbolTable.getInstance().table;
    for (Symbol symbol : table) {
      if (symbol.getName().equals(name)) {
        if (symbol.getScope().equals(scope))
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

  public static void dumpTable(Writer writer) {
    List<Symbol>table = SymbolTable.getInstance().table;
    List<Function>functions = SymbolTable.getInstance().functions;
    try {
      writer.write("# Variables\n");
      for (Symbol symbol : table) {
        writer.write(symbol + "\n");
      }
      writer.write("# Functions\n");
      for (Function func : functions) {
        writer.write(func + "\n");
      }
    } catch (IOException e) {
      System.err.println("Error writing symbol table.");
    }
  }
}
