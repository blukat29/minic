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

    /* Semantic analysis of the AST */
    program.analyse();
    int errorCount = program.getErrorCount();
    int warnCount = program.getWarnCount();
    System.out.printf("Compile complete. %d errors, %d warnings.\n", errorCount, warnCount);

    /* Print AST representation and dump symbol table. */
    if (errorCount == 0) {
      Printer.setASTWriter(treeWriter);
      program.dumpAST(0);
      SymbolTable.getInstance().dumpTable(tableWriter);
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
