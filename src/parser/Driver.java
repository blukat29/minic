package parser;
import java.io.*;

public class Driver
{
  public static void main(String[] args) throws Exception
  {
    Reader input = new BufferedReader(new InputStreamReader(System.in));
    Parser p = new Parser(new Lexer(input));
    try
    {
      System.out.println(p.parse());
    }
    catch (Exception e)
    {
      System.err.println("Error: " + e.getMessage());
    }
  }
}
