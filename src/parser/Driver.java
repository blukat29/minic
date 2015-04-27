package parser;
import ast.*;
import symbol.*;
import java.io.*;
import java_cup.runtime.ScannerBuffer;
import java_cup.runtime.ComplexSymbolFactory;
import java_cup.runtime.ComplexSymbolFactory.ComplexSymbol;

public class Driver
{
  private static void run(InputStream source, Writer tree, Writer table)
  {
    Reader input = new BufferedReader(new InputStreamReader(source));
    ComplexSymbolFactory csf = new ComplexSymbolFactory();
    ScannerBuffer lexer = new ScannerBuffer(new Lexer(input, csf));
    Parser parser = new Parser(lexer, csf);

    ComplexSymbol startSymbol = null;
    Program program = null;
    try {
      startSymbol = (ComplexSymbol)parser.parse();
      program = (Program)startSymbol.value;
      ASTWriter.setWriter(tree);
    }
    catch (Exception e) {
      System.err.println("Parse error: " + e.getMessage());
      return;
    }
    program.dumpAST(0);

    try {
      program.compile();
      table.write(SymbolTable.getInstance().toString());
    }
    catch (Exception e) {
      System.err.println("Compile error: " + e.getMessage());
      return;
    }
  }

  public static void main(String[] args) throws Exception
  {
    Writer tree = new BufferedWriter(new FileWriter("tree.txt"));
    Writer table = new BufferedWriter(new FileWriter("table.txt"));
    run(System.in, tree, table);
    tree.close();
    table.close();
  }
}
