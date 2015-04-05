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
FLOATNUM = [0-9]+\.[0-9]+

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
    ":"             { return symbol( Symbols.COLON ); }

    "return"        { return symbol( Symbols.RETURN ); }
    "while"         { return symbol( Symbols.WHILE ); }
    "do"            { return symbol( Symbols.DO ); }
    "for"           { return symbol( Symbols.FOR ); }
    "if"            { return symbol( Symbols.IF ); }
    "else"          { return symbol( Symbols.ELSE ); }
    "switch"        { return symbol( Symbols.SWITCH ); }
    "case"          { return symbol( Symbols.CASE ); }
    "break"         { return symbol( Symbols.BREAK ); }
    "default"       { return symbol( Symbols.DEFAULT ); }

    "-"             { return symbol( Symbols.MINUS ); }
    "*"             { return symbol( Symbols.TIMES ); }
    "/"             { return symbol( Symbols.DIVIDE ); }
    "+"             { return symbol( Symbols.PLUS ); }
    "<"             { return symbol( Symbols.LT ); }
    ">"             { return symbol( Symbols.GT ); }
    "<="            { return symbol( Symbols.LE ); }
    ">="            { return symbol( Symbols.GE ); }
    "=="            { return symbol( Symbols.EQEQ ); }
    "!="            { return symbol( Symbols.NE ); }

    {ID}            { return symbol( Symbols.ID, yytext() ); }
    {INTNUM}        { return symbol( Symbols.INTNUM, new Integer( yytext() ) ); }
    {FLOATNUM}      { return symbol( Symbols.INTNUM, new Float( yytext() ) ); }
    {WhiteSpace}    { /* ignore */ }
}

/* error fallback */
.|\n                             { throw new Error("Illegal character <"+
                                                    yytext()+">"); }
