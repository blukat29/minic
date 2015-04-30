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
    SourceManager sm = new SourceManager(source);
    Pos.setSourceManager(sm);
    Reader input = sm.getReader();
    ComplexSymbolFactory csf = new ComplexSymbolFactory();
    ScannerBuffer lexer = new ScannerBuffer(new Lexer(input, csf));
    Parser parser = new Parser(lexer, csf);

    /* Parse the program */
    ComplexSymbol startSymbol = null;
    Program program = null;
    try {
      startSymbol = (ComplexSymbol)parser.parse();
      program = (Program)startSymbol.value;
    }
    catch (Exception e) {
      System.err.println("Parse error: " + e.getMessage());
      return;
    }

    /* Print AST representation */
    Printer.setASTWriter(tree);
    program.dumpAST(0);

    /* Compile the AST */
    try {
      program.compile();
    }
    catch (Exception e) {
      System.err.println("Compile error: " + e.getMessage());
      return;
    }

    /* Dump symbol table */
    try {
      SymbolTable.getInstance().dumpTable(table);
    } catch (IOException e) {
      System.err.println("Error writing 'table.txt'");
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
