package parser;
import ast.*;
import symbol.*;
import java.io.*;
import java_cup.runtime.ScannerBuffer;
import java_cup.runtime.ComplexSymbolFactory;
import java_cup.runtime.ComplexSymbolFactory.ComplexSymbol;

public class Driver
{
  private static void run(InputStream source, Writer treeWriter, Writer tableWriter)
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
      e.printStackTrace();
      return;
    }

    /* Print AST representation */
    Printer.setASTWriter(treeWriter);
    program.dumpAST(0);

    /* Compile the AST */
    try {
      program.compile();
    }
    catch (Exception e) {
      System.err.println("Compile error: " + e.getMessage());
      e.printStackTrace();
      return;
    }

    /* Dump symbol table */
    try {
      SymbolTable.getInstance().dumpTable(tableWriter);
    } catch (IOException e) {
      System.err.println("Error writing 'table.txt'");
      return;
    }
  }

  public static void main(String[] args) throws Exception
  {
    InputStream source = null;
    if (args.length > 0)
      source = new FileInputStream(args[0]);
    else
      source = System.in;
    Writer treeWriter = new BufferedWriter(new FileWriter("tree.txt"));
    Writer tableWriter = new BufferedWriter(new FileWriter("table.txt"));

    run(source, treeWriter, tableWriter);

    if (args.length > 0)
      source.close();
    treeWriter.close();
    tableWriter.close();
  }
}
