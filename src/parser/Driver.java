package parser;
import java.io.*;
import javax.xml.stream.XMLOutputFactory;
import javax.xml.stream.XMLStreamWriter;
import javax.xml.transform.*;
import javax.xml.transform.stream.*;

import java_cup.runtime.ScannerBuffer;
import java_cup.runtime.XMLElement;
import java_cup.runtime.Symbol;
import java_cup.runtime.ComplexSymbolFactory;
import java_cup.runtime.ComplexSymbolFactory.ComplexSymbol;

public class Driver
{
  public static void writeXML(String filename, ScannerBuffer lexer, ComplexSymbol s)
  {
    try
    {
      XMLElement e = (XMLElement)s.value;
      XMLOutputFactory outFactory = XMLOutputFactory.newInstance();
      XMLStreamWriter sw = outFactory.createXMLStreamWriter(new FileOutputStream("ast.xml"));
      XMLElement.dump(lexer, sw, e, "Expr");

      Transformer tr = TransformerFactory.newInstance()
        .newTransformer(new StreamSource(new File("tree.xsl")));
      Source text = new StreamSource(new File("ast.xml"));
      tr.transform(text, new StreamResult(new File("view.html")));

    }
    catch (Exception e)
    {
      System.err.println("error writing XML file");
    }
  }

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
      System.out.println(s);
      writeXML("out.html", lexer, s);
    }
    catch (Exception e)
    {
      System.err.println("error: " + e.getMessage());
    }
  }
}
