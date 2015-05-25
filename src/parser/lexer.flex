package parser;
import java.io.*;
import java_cup.runtime.*;
import java_cup.runtime.ComplexSymbolFactory.*;

import ast.*;

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
  ComplexSymbolFactory sf;

  public Lexer(Reader in, ComplexSymbolFactory sf) {
    this(in);
    this.sf = sf;
  }

  private Symbol symbol(int type) {
    Location left = new Location(yyline+1, yycolumn+1, yychar);
    Location right = new Location(yyline+1, yycolumn+yylength(), yychar+yylength());
    return sf.newSymbol(Symbols.terminalNames[type], type, left, right);
  }

  private Symbol symbol(int type, Object value) {
    Location left = new Location(yyline+1, yycolumn+1, yychar);
    Location right = new Location(yyline+1, yycolumn+yylength(), yychar+yylength());
    return sf.newSymbol(Symbols.terminalNames[type], type, left, right, value);
  }

  private Pos currentPos() {
    Location left = new Location(yyline+1, yycolumn+1, yychar);
    Location right = new Location(yyline+1, yycolumn+yylength(), yychar+yylength());
    return new Pos(left, right);
  }

  private void error(String message) {
    System.err.println("Lexer error at line " + (yyline+1) + ", column " + (yycolumn+1) + " : " + message);
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
    "int"           { return symbol( Symbols.TYPE, new TypeInfo( TypeInfo.INT ) ); }
    "float"         { return symbol( Symbols.TYPE, new TypeInfo( TypeInfo.FLOAT ) ); }

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

    "-"             { return symbol( Symbols.MINUS, "-" ); }
    "*"             { return symbol( Symbols.TIMES, "*" ); }
    "/"             { return symbol( Symbols.DIVIDE, "/" ); }
    "+"             { return symbol( Symbols.PLUS, "+" ); }
    "<"             { return symbol( Symbols.LT, "<" ); }
    ">"             { return symbol( Symbols.GT, ">" ); }
    "<="            { return symbol( Symbols.LE, "<=" ); }
    ">="            { return symbol( Symbols.GE, ">=" ); }
    "=="            { return symbol( Symbols.EQEQ, "==" ); }
    "!="            { return symbol( Symbols.NE, "!=" ); }

    {ID}            { return symbol( Symbols.ID, yytext() ); }
    {INTNUM}        { return symbol( Symbols.INTNUM, new IntNum( currentPos(), yytext() ) ); }
    {FLOATNUM}      { return symbol( Symbols.FLOATNUM, new FloatNum( currentPos(), yytext() ) ); }
    {WhiteSpace}    { /* ignore */ }
}

/* error fallback */
[^]                             { throw new Error("Illegal character <"+
                                                    yytext()+">"); }
