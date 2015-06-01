package parser;
import ast.*;
import symbol.*;
import java.io.*;
import java_cup.runtime.ScannerBuffer;
import java_cup.runtime.ComplexSymbolFactory;
import java_cup.runtime.ComplexSymbolFactory.ComplexSymbol;

public class Driver
{
  private static boolean run(InputStream source, Writer treeWriter, Writer tableWriter, Writer codeWriter)
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
      return false;
    }

    /* Semantic analysis of the AST */
    program.analyse();
    int errorCount = program.getErrorCount();
    int warnCount = program.getWarnCount();
    System.out.printf("Compile complete. %d errors, %d warnings.\n", errorCount, warnCount);

    if (errorCount == 0) {
      /* Print AST representation and dump symbol table. */
      Printer.setASTWriter(treeWriter);
      program.dumpAST(0);
      SymbolTable.getInstance().dumpTable(tableWriter);
      /* Generate assembly code */
      Printer.setCodeWriter(codeWriter);
      program.codegen();
      return true;
    }
    else {
      return false;
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
    Writer codeWriter = new BufferedWriter(new FileWriter("code.T"));

    boolean success = run(source, treeWriter, tableWriter, codeWriter);

    if (args.length > 0)
      source.close();
    treeWriter.close();
    tableWriter.close();
    codeWriter.close();

    if (success) System.exit(0);
    else System.exit(1);
  }
}
