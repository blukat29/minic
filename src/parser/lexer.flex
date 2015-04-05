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

ID = [A-Za-z][A-Za-z0-9_]*
INTNUM = [0-9]+

%%

<YYINITIAL>
{
    "int"           { return symbol( Symbols.TYPE, "int" ); }
    "float"         { return symbol( Symbols.TYPE, "float" ); }
    ";"             { return symbol( Symbols.SEMI ); }
    "["             { return symbol( Symbols.LBRACKET ); }
    "]"             { return symbol( Symbols.RBRACKET ); }
    "{"             { return symbol( Symbols.LBRACE ); }
    "}"             { return symbol( Symbols.RBRACE ); }
    "("             { return symbol( Symbols.LPAREN ); }
    ")"             { return symbol( Symbols.RPAREN ); }
    ","             { return symbol( Symbols.COMMA ); }
    "="             { return symbol( Symbols.EQUALS ); }
    "return"        { return symbol( Symbols.RETURN ); }
    "while"         { return symbol( Symbols.WHILE ); }
    "do"            { return symbol( Symbols.DO ); }
    "for"           { return symbol( Symbols.FOR ); }
    "if"            { return symbol( Symbols.IF ); }
    "else"          { return symbol( Symbols.ELSE ); }
    {ID}            { return symbol( Symbols.ID, yytext() ); }
    {INTNUM}        { return symbol( Symbols.INTNUM, new Integer( yytext() ) ); }
    {WhiteSpace}    { /* ignore */ }
}

/* error fallback */
.|\n                             { throw new Error("Illegal character <"+
                                                    yytext()+">"); }
