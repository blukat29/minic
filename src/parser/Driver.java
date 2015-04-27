package parser;
import ast.*;
import symbol.*;
import java.io.*;
import java_cup.runtime.ScannerBuffer;
import java_cup.runtime.ComplexSymbolFactory;
import java_cup.runtime.ComplexSymbolFactory.ComplexSymbol;

public class Driver
{
  public static void main(String[] args) throws Exception
  {
    Reader input = new BufferedReader(new InputStreamReader(System.in));
    ComplexSymbolFactory csf = new ComplexSymbolFactory();
    ScannerBuffer lexer = new ScannerBuffer(new Lexer(input, csf));
    Parser parser = new Parser(lexer, csf);
    try
    {
      ComplexSymbol startSymbol = (ComplexSymbol)parser.parse();
      Program p = (Program)startSymbol.value;
      System.out.println(p);
      System.out.println(SymbolTable.getInstance());
    }
    catch (Exception e)
    {
      System.err.println("error: " + e.getMessage());
    }
  }
}
