package parser;
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
    Parser p = new Parser(lexer, csf);
    ComplexSymbol s;
    try
    {
      s = (ComplexSymbol)p.parse();
      System.out.println(s.value);
    }
    catch (Exception e)
    {
      System.err.println("error: " + e.getMessage());
    }
  }
}
