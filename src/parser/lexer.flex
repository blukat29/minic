package parser;
import java_cup.runtime.*;

%%
%public
%class Lexer
%cup
%line
%column
%eofval{
    return symbol( Symbols.EOF );
%eofval}

%{
  StringBuffer string = new StringBuffer();

  private Symbol symbol(int type) {
    return new Symbol(type, yyline, yycolumn);
  }
  private Symbol symbol(int type, Object value) {
    return new Symbol(type, yyline, yycolumn, value);
  }

  public static void init() throws java.io.IOException {}
%}

LineTerminator = \r|\n|\r\n
WhiteSpace     = {LineTerminator} | [ \t\f]

IntLiteral = 0 | [1-9][0-9]*

%%

<YYINITIAL>
{
    "+"             { return symbol( Symbols.PLUS ); }
    ";"             { return symbol( Symbols.SEMI ); }
    {IntLiteral}    { return symbol( Symbols.NUMBER, new Integer( yytext() ) ); }
    {WhiteSpace}    { /* ignore */ }
}

/* error fallback */
.|\n                             { throw new Error("Illegal character <"+
                                                    yytext()+">"); }
